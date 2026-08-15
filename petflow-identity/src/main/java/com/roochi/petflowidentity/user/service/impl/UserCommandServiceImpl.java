package com.roochi.petflowidentity.user.service.impl;

import com.roochi.petflowshared.security.SecurityUtils;
import com.roochi.petflowidentity.user.dto.role.AssignRoleRequestDto;
import com.roochi.petflowidentity.user.dto.role.AssignRoleResponseDto;
import com.roochi.petflowidentity.user.dto.user.*;
import com.roochi.petflowidentity.user.entity.Role;
import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowidentity.user.entity.UserClinic;
import com.roochi.petflowidentity.user.entity.UserClinicRole;
import com.roochi.petflowidentity.user.finder.RoleFinder;
import com.roochi.petflowidentity.user.finder.UserFinder;
import com.roochi.petflowidentity.user.repository.RoleRepository;
import com.roochi.petflowidentity.user.repository.UserRepository;
import com.roochi.petflowidentity.user.repository.UserClinicRepository;
import com.roochi.petflowidentity.user.repository.UserClinicRoleRepository;
import com.roochi.petflowidentity.user.service.command.UserCommandService;
import com.roochi.petflowshared.enums.UserStatus;
import com.roochi.petflowshared.exception.AlreadyExistsException;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final UserClinicRepository userClinicRepository;
    private final UserClinicRoleRepository userClinicRoleRepository;
    private final RoleRepository roleRepository;
    private final SecurityUtils securityUtils;
    private final UserFinder userFinder;
    private final RoleFinder roleFinder;

    @Override
    public AddUserResponseDto addUser(AddUserRequestDto requestDto) {

        //Check duplicate mobile
        if (userRepository.existsByMobile(requestDto.getMobile()))
            throw new AlreadyExistsException(ErrorCode.USER_ALREADY_EXISTS);

        //Current Clinic
        Long clinicId = securityUtils.getCurrentClinicId();

        //Create User
        User user = new User();
        user.setMobile(requestDto.getMobile());
        user.setUserType(requestDto.getUserType());
        user.setPassword(null);
        user.setStatus(UserStatus.ACTIVE);
        user.setMobileVerified(false);
        user.setEnabled(true);
        user.setDeleted(false);
        user = userRepository.save(user);

        //Create Membership
        UserClinic userClinic = new UserClinic();
        userClinic.setClinicId(clinicId);
        userClinic.setUser(user);
        userClinic.setActive(true);
        userClinic.setDefaultClinic(false);
        userClinic = userClinicRepository.save(userClinic);

        //Assign Roles
        for (String roleCode : requestDto.getRoles()) {
            Role role = roleFinder.findByCode(roleCode);
            UserClinicRole relation = new UserClinicRole();
            relation.setUserClinic(userClinic);
            relation.setRole(role);
            userClinicRoleRepository.save(relation);
        }

        //TODO
        //ارسال رمز موقت از طریق communication Module

        AddUserResponseDto responseDto = new AddUserResponseDto();
        responseDto.setId(user.getId());
        return responseDto;
    }

    @Override
    public UpdateUserResponseDto updateUser(UpdateUserRequestDto requestDto) {
        var user = userRepository.findById(requestDto.getId()).orElseThrow(() ->
                new NotFoundException(ErrorCode.USER_NOT_FOUND));
        user.setUserType(requestDto.getUserType());
        userRepository.save(user);

        Long clinicId = securityUtils.getCurrentClinicId();
        UserClinic userClinic = userClinicRepository.findByUserIdAndClinicId(user.getId(), clinicId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CLINIC_NOT_FOUND));

        userClinic.setActive(requestDto.getActive());

        userClinicRepository.save(userClinic);

        userClinicRoleRepository.deleteByUserClinicId(clinicId);
        for (Long roleId : requestDto.getRolesIds()) {
            Role role = roleRepository.findById(roleId).orElseThrow(
                    () -> new NotFoundException(ErrorCode.CLINIC_NOT_FOUND));
            UserClinicRole userClinicRole = new UserClinicRole();
            userClinicRole.setUserClinic(userClinic);
            userClinicRole.setRole(role);
            userClinicRoleRepository.save(userClinicRole);
        }
        UpdateUserResponseDto response = new UpdateUserResponseDto();
        response.setId(user.getId());
        return response;
    }

    @Override
    public DeleteUserResponseDto deleteUser(DeleteUserRequestDto requestDto) {
        Long clinicId = securityUtils.getCurrentClinicId();
        var user = userRepository.findById(requestDto.getId()).orElseThrow(() ->
                new NotFoundException(ErrorCode.USER_NOT_FOUND));
        UserClinic userClinic = userClinicRepository.findByUserIdAndClinicId(user.getId(), clinicId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CLINIC_NOT_FOUND));
        userClinicRoleRepository.deleteByUserClinicId(userClinic.getId());
        userClinic.setDeleted(true);
        userClinicRepository.save(userClinic);
        userClinicRepository.delete(userClinic);

        if (!userClinicRepository.existsByUserId(user.getId())) {
            user.setDeleted(true);
            userRepository.save(user);
        }
        return new DeleteUserResponseDto();
    }

    @Override
    public AssignRoleResponseDto assignRoles(AssignRoleRequestDto requestDto) {
        var user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        Long clinicId = securityUtils.getCurrentClinicId();
        UserClinic userClinic = userClinicRepository.findByUserIdAndClinicId(user.getId(), clinicId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CLINIC_NOT_FOUND));
        userClinicRoleRepository.deleteByUserClinicId(userClinic.getId());

        for (Long roleId : requestDto.getRoleIds()) {
            Role role = roleFinder.findById(roleId);
            UserClinicRole userClinicRole = new UserClinicRole();
            userClinicRole.setUserClinic(userClinic);
            userClinicRole.setRole(role);
            userClinicRoleRepository.save(userClinicRole);
        }
        return new AssignRoleResponseDto();
    }

    @Override
    public RemoveRoleResponseDto removeRole(RemoveRoleRequestDto requestDto) {
        var user = userFinder.findById(requestDto.getUserId());
        Long clinicId = securityUtils.getCurrentClinicId();

        UserClinic userClinic = userClinicRepository.findByUserIdAndClinicId(user.getId(), clinicId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CLINIC_NOT_FOUND));
        Role role = roleFinder.findByCode(requestDto.getRoleCode());
        UserClinicRole userClinicRole = userClinicRoleRepository.findByUserClinicIdAndRole(userClinic.getId(), role)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CLINIC_NOT_FOUND));

        userClinicRoleRepository.delete(userClinicRole);
        return new RemoveRoleResponseDto();

    }

    @Override
    public UserActiveResponseDto userActive(UserActiveRequestDto requestDto) {
        changeUserActivation(requestDto.getUserId(), true);
        return new UserActiveResponseDto();
    }

    @Override
    public UserDeactivateResponseDto userDeactivate(UserDeactivateRequestDto requestDto) {
        changeUserActivation(requestDto.getUserId(), false);
        return new UserDeactivateResponseDto();
    }

    private void changeUserActivation(Long userId, boolean active) {
        var user = userFinder.findById(userId);
        Long clinicId = securityUtils.getCurrentClinicId();
        UserClinic userClinic = userClinicRepository.findByUserIdAndClinicId(user.getId(), clinicId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        userClinic.setActive(active);
        userClinicRepository.save(userClinic);
    }
}
