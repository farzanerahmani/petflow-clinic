package com.roochi.petflowidentity.auth.service.impl;

import com.roochi.petflowidentity.auth.dto.request.*;
import com.roochi.petflowidentity.auth.dto.response.LoginResponseDto;
import com.roochi.petflowidentity.auth.dto.response.LogoutResponseDto;
import com.roochi.petflowidentity.auth.dto.response.RefreshTokenResponseDto;
import com.roochi.petflowidentity.auth.dto.response.SendOtpResponsetDto;
import com.roochi.petflowidentity.auth.mapper.AuthMapper;
import com.roochi.petflowidentity.auth.refreshtoken.service.RefreshTokenService;
import com.roochi.petflowidentity.auth.service.command.AuthCommandService;
import com.roochi.petflowidentity.auth.validator.AuthValidator;
import com.roochi.petflowidentity.jwt.JwtProperties;
import com.roochi.petflowidentity.jwt.JwtService;
import com.roochi.petflowidentity.otp.entity.Otp;
import com.roochi.petflowidentity.otp.repository.OtpRepository;
import com.roochi.petflowidentity.otp.service.OtpService;
import com.roochi.petflowshared.security.JwtAuthentication;
import com.roochi.petflowshared.security.SecurityUtils;
import com.roochi.petflowidentity.user.entity.Role;
import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowidentity.user.entity.UserClinic;
import com.roochi.petflowidentity.user.entity.UserClinicRole;
import com.roochi.petflowidentity.user.finder.RoleFinder;
import com.roochi.petflowidentity.user.finder.UserFinder;
import com.roochi.petflowidentity.user.finder.UserClinicFinder;
import com.roochi.petflowidentity.user.finder.UserClinicRoleFinder;
import com.roochi.petflowidentity.user.repository.UserRepository;
import com.roochi.petflowidentity.user.repository.UserClinicRepository;
import com.roochi.petflowidentity.user.repository.UserClinicRoleRepository;
import com.roochi.petflowshared.enums.UserStatus;
import com.roochi.petflowshared.exception.AlreadyExistsException;
import com.roochi.petflowshared.exception.DeactivateException;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.UnauthorizedException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author farzane.rahmani
 * @created 7/2/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AuthCommandServiceImpl implements AuthCommandService {
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;
    private final OtpService otpService;
    private final RefreshTokenService refreshTokenService;
    private final UserFinder userFinder;
    private final UserClinicRoleFinder userClinicRoleFinder;
    private final UserClinicRoleRepository userClinicRoleRepository;
    private final UserClinicFinder userClinicFinder;
    private final UserClinicRepository userClinicRepository;
    private final RoleFinder roleFinder;

    private final AuthValidator authValidator;
    private final AuthMapper authMapper;
    private final SecurityUtils securityUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpRepository otpRepository;


    @Override
    public SendOtpResponsetDto sendOtp(SendOtpRequestDto requestDto) {
        User user = userFinder.findByMobile(requestDto.getMobile());
        if (user == null)
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        otpRepository.deleteByMobile(requestDto.getMobile());
        String code = otpService.generateOtp();
        Otp otp = new Otp();
        otp.setMobile(requestDto.getMobile());
        otp.setCode(code);
        otp.setExpiredAt(LocalDateTime.now().plusMinutes(2));
        otp.setUsed(false);
        otpRepository.save(otp);

        // TODO
        //smsService.sendOtp(requestDto.getMobile(),otp.getCode());
        return new SendOtpResponsetDto();
    }

    @Override
    public LoginResponseDto verifyOtp(VerifyOtpRequestDto requestDto) {

        Otp otp = otpService.verify(requestDto.getMobile(), requestDto.getCode());
        if (otp.isUsed())
            throw new AlreadyExistsException(ErrorCode.CLINIC_NOT_FOUND);

        if (otp.getExpiredAt().isBefore(LocalDateTime.now()))
            throw new AlreadyExistsException(ErrorCode.CLINIC_NOT_FOUND);

        otp.setUsed(true);
        otpRepository.save(otp);

        User user = userFinder.findByMobile(requestDto.getMobile());

        if (Boolean.TRUE.equals(user.getDeleted()))
            throw new NotFoundException(ErrorCode.CLINIC_NOT_FOUND);
        if (!Boolean.TRUE.equals(user.getEnabled()))
            throw new DeactivateException(ErrorCode.USER_DISABLED);

        List<UserClinic> userClinics = userClinicRepository.findAllByUserIdAndDeletedFalse(user.getId());

        if (userClinics.isEmpty())
            throw new NotFoundException(ErrorCode.CLINIC_NOT_FOUND);
        LoginResponseDto response = new LoginResponseDto();

        if (userClinics.size() > 1) {
            response.setNeedSelectClinic(true);
            response.setNeedSetPin(user.getPassword() == null);
            response.setTemporaryToken(jwtService.generateTemporaryToken(user.getId()));
            return response;
        }
        UserClinic userClinic = userClinics.getFirst();
        List<String> roles =
                userClinicRoleRepository.findAllByUserClinicId(userClinic.getId())
                        .stream()
                        .map(UserClinicRole::getRole)
                        .map(Role::getCode)
                        .toList();
        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        JwtAuthentication authentication =
                JwtAuthentication.builder()
                        .userId(user.getId())
                        .mobile(user.getMobile())
                        .clinicId(userClinic.getClinicId())
                        .userClinicId(userClinic.getId())
                        .userType(user.getUserType())
                        .roles(new HashSet<>(roles))
                        .authorities(authorities).build();
        response.setNeedSetPin(user.getPassword() == null);
        response.setNeedSelectClinic(false);
        response.setAccessToken(jwtService.generateAccessToken(authentication));
        response.setRefreshToken(jwtService.generateRefreshToken(user.getId()));
        return response;


    }

    @Override
    public LoginResponseDto setPin(SetPinRequestDto requestDto) {
        User user = userFinder.findByMobile(requestDto.getMobile());
        if (user.getPassword() != null)
            throw new AlreadyExistsException(ErrorCode.USER_ALREADY_EXISTS);

        user.setPassword(passwordEncoder.encode(requestDto.getPin()));
        userRepository.save(user);
        List<UserClinic> userClinics = userClinicRepository.findAllByUserIdAndDeletedFalse(user.getId());
        if (userClinics.isEmpty())
            throw new NotFoundException(ErrorCode.CLINIC_NOT_FOUND);
        LoginResponseDto response = new LoginResponseDto();
        response.setNeedSetPin(false);

        if (userClinics.size() > 1) {
            response.setNeedSelectClinic(true);
            return response;
        }
        UserClinic userClinic = userClinics.getFirst();
        List<String> roles =
                userClinicRoleRepository.findAllByUserClinicId(userClinic.getId())
                        .stream()
                        .map(UserClinicRole::getRole)
                        .map(Role::getCode)
                        .toList();
        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        JwtAuthentication authentication =
                JwtAuthentication.builder()
                        .userId(user.getId())
                        .mobile(user.getMobile())
                        .clinicId(userClinic.getClinicId())
                        .userClinicId(userClinic.getId())
                        .userType(user.getUserType())
                        .roles(new HashSet<>(roles))
                        .authorities(authorities).build();

        response.setNeedSelectClinic(false);
        response.setAccessToken(jwtService.generateAccessToken(authentication));
        response.setRefreshToken(jwtService.generateRefreshToken(user.getId()));
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDto selectClinic(SelectClinicRequestDto requestDto) {

        UserClinic userClinic = userClinicFinder.findById(requestDto.getUserClinicId());
        if (Boolean.TRUE.equals(userClinic.getDeleted()))
            throw new NotFoundException(ErrorCode.CLINIC_NOT_FOUND);

        if (!Boolean.TRUE.equals(userClinic.isActive()))
            throw new NotFoundException(ErrorCode.CLINIC_NOT_FOUND);

        User user = userClinic.getUser();
        if (Boolean.TRUE.equals(user.getDeleted()))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        if (!UserStatus.ACTIVE.equals(user.getStatus()))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        List<String> roles =
                userClinicRoleRepository.findAllByUserClinicId(userClinic.getId())
                        .stream()
                        .map(UserClinicRole::getRole)
                        .map(Role::getCode)
                        .toList();
        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        JwtAuthentication authentication =
                JwtAuthentication.builder()
                        .userId(user.getId())
                        .mobile(user.getMobile())
                        .clinicId(userClinic.getClinicId())
                        .userClinicId(userClinic.getId())
                        .userType(user.getUserType())
                        .roles(new HashSet<>(roles))
                        .authorities(authorities).build();

        String accessToken = jwtService.generateAccessToken(authentication);
        String refreshToken = jwtService.generateRefreshToken(user.getId());
        LoginResponseDto response = new LoginResponseDto();
        response.setNeedSelectClinic(false);
        response.setNeedSetPin(user.getPassword() == null);
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        return response;
        //TODO:
        //بعدا امنیت متد تکمیل شود
        //بعد از verifyOTp یک temporary login token صادر شود
        //ابتدا در selectTenant temporaryToken اعتبار سنجی شود
        //userTenantId انتخاب و بررسی شود متعلق به همان کاربر است یا نه
        //سپس otp را که تایید کرده است
        //accessToken صادر شود

    }

    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) throws NotFoundException {
        User user = userRepository.findByMobile(requestDto.getMobile())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        if (Boolean.TRUE.equals(user.getDeleted()))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        if (!Boolean.TRUE.equals(user.getEnabled()))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        if (user.getStatus().equals(UserStatus.ACTIVE))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        //Pin should already be defined
        if (user.getPassword() == null)
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        //check pin
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword()))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        List<UserClinic> userClinics =
                userClinicRepository.findAllByUserIdAndDeletedFalse(user.getId());

        if (userClinics.isEmpty())
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        LoginResponseDto response = new LoginResponseDto();
        response.setNeedSetPin(false);
        //user belongs to multiple clinics
        if (userClinics.size() > 1) {
            response.setNeedSelectClinic(true);

            //TODO:
            //Return Temporary Login token
            //In selectTenant validate temporary token and ensure
            //selected userClinic belongs to authenticated user
            return response;
        }
        UserClinic userClinic = userClinics.getFirst();
        if (Boolean.TRUE.equals(userClinic.getDeleted()))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        if (!Boolean.TRUE.equals(userClinic.isActive()))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        List<String> roles =
                userClinicRoleRepository.findAllByUserClinicId(userClinic.getId())
                        .stream()
                        .map(UserClinicRole::getRole)
                        .map(Role::getCode)
                        .toList();
        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        JwtAuthentication authentication =
                JwtAuthentication.builder()
                        .userId(user.getId())
                        .mobile(user.getMobile())
                        .clinicId(userClinic.getClinicId())
                        .userClinicId(userClinic.getId())
                        .userType(user.getUserType())
                        .roles(new HashSet<>(roles))
                        .authorities(authorities).build();

        response.setNeedSelectClinic(false);
        response.setAccessToken(jwtService.generateAccessToken(authentication));
        response.setRefreshToken(jwtService.generateRefreshToken(user.getId()));
        return response;

    }

    @Override
    @Transactional(readOnly = true)
    public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto requestDto) {
        if (jwtService.isTokenValid(requestDto.getRefreshToken()))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        Long userId = jwtService.extractUserId(requestDto.getRefreshToken());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        if (Boolean.TRUE.equals(user.getDeleted()))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        if (!Boolean.TRUE.equals(user.getEnabled()))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        if (!user.getStatus().equals(UserStatus.ACTIVE))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        //ToDo:
        //Multi-tenant supports:
        //Do not use defaultTenant for refresh token
        //Refresh token should contain user tenant and tenant claims,
        //or be persisted with the selected user tenant.
        //During refresh , issue the new access token for same tenant
        //that the user selected during login.
        UserClinic userClinic = userClinicRepository.findByUserIdAndDefaultClinicTrueAndDeletedFalse(user.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        List<String> roles =
                userClinicRoleRepository.findAllByUserClinicId(userClinic.getId())
                        .stream()
                        .map(UserClinicRole::getRole)
                        .map(Role::getCode)
                        .toList();
        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        JwtAuthentication authentication =
                JwtAuthentication.builder()
                        .userId(user.getId())
                        .mobile(user.getMobile())
                        .clinicId(userClinic.getClinicId())
                        .userClinicId(userClinic.getId())
                        .userType(user.getUserType())
                        .roles(new HashSet<>(roles))
                        .authorities(authorities).build();
        RefreshTokenResponseDto response = new RefreshTokenResponseDto();
        response.setAccessToken(jwtService.generateAccessToken(authentication));
        response.setRefreshToken(jwtService.generateRefreshToken(user.getId()));
        response.setExpiresIn(jwtProperties.getAccessTokenExpiration());
        return response;

    }

    @Override
    public LogoutResponseDto logout(LogoutRequestDto requestDto) {
        if(jwtService.isTokenValid(requestDto.getRefreshToken()))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        //ToDo:
        //persist refresh token (or its jti) in database.
        //on logout revoke the token.
        //JwtAuthenticationFilter and refresh token endpoint
        ///should reject revoked tokens.
        return new LogoutResponseDto();
    }

    private User findUser(String mobile) {
        return userFinder.findByMobile(mobile);
    }

    private void validateUser(User user) {
        if (!UserStatus.ACTIVE.equals(user.getStatus()))
            throw new UnauthorizedException(ErrorCode.USER_DISABLED);
    }

    private void validatePassword(String credential, User user) {
        if (!passwordEncoder.matches(credential, user.getPassword()))
            throw new UnauthorizedException(ErrorCode.INVALID_CREDENTIALS);
    }

//    private LoginResponseDto buildLoginResponse(User user, String accessToken, String refreshToken) {
//        return LoginResponseDto.builder()
//                .userId(user.getId())
//                .mobile(user.getMobile())
//                .fullName(user.getFirstName())
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .expiresIn(jwtService.getAccessTokenExpiration()).build();
//    }

}
