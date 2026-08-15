package com.roochi.petflowidentity.user.service.impl;

import com.roochi.petflowshared.security.SecurityUtils;
import com.roochi.petflowidentity.user.dto.user.*;
import com.roochi.petflowidentity.user.entity.Role;
import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowidentity.user.entity.UserClinic;
import com.roochi.petflowidentity.user.entity.UserClinicRole;
import com.roochi.petflowidentity.user.finder.UserFinder;
import com.roochi.petflowidentity.user.finder.UserClinicFinder;
import com.roochi.petflowidentity.user.mapper.RoleMapper;
import com.roochi.petflowidentity.user.mapper.UserMapper;
import com.roochi.petflowidentity.user.repository.UserClinicRepository;
import com.roochi.petflowidentity.user.repository.UserClinicRoleRepository;
import com.roochi.petflowidentity.user.service.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {
    private final UserMapper userMapper;
    private final SecurityUtils securityUtils;
    private final UserClinicFinder userClinicFinder;
    private final UserClinicRoleRepository userClinicRoleRepository;
    private final RoleMapper roleMapper;
    private final UserFinder userFinder;

    private final UserClinicRepository userClinicRepository;

    @Override
    public GetUserByIdResponseDto getUserById(GetUserByIdRequestDto requestDto) {
        Long clinicId = securityUtils.getCurrentClinicId();
        UserClinic userClinic = userClinicFinder.
                findByUserIdAndClinicId(requestDto.getUserId(), clinicId);
        User user = userClinic.getUser();
        var roles = userClinicRoleRepository.findByUserClinicId(userClinic.getId())
                .stream().map(item -> roleMapper.toRoleDto(item.getRole())).collect(Collectors.toSet());

        GetUserByIdResponseDto responseDto = new GetUserByIdResponseDto();

        var userDto = userMapper.toUserDto(user);
        userDto.setRoles(roles);
        return responseDto;
    }

    @Override
    public GetUserByMobileResponseDto getUserByMobile(GetUserByMobileRequestDto requestDto) {

        Long clinicId = securityUtils.getCurrentClinicId();
        User user = userFinder.findByMobile(requestDto.getMobile());
        UserClinic userClinic = userClinicFinder.findByUserIdAndClinicId(user.getId(), clinicId);
        List<Role> roles = userClinicRoleRepository.findByUserClinicId(userClinic.getId())
                .stream().map(UserClinicRole::getRole).toList();
        var userDto = userMapper.toUserDto(user);
        userDto.setRoles(roles.stream().map(roleMapper::toRoleDto).collect(Collectors.toSet()));
        GetUserByMobileResponseDto responseDto = new GetUserByMobileResponseDto();
        responseDto.setUser(userDto);
        responseDto.setActive(userClinic.isActive());
        return responseDto;
    }

    @Override
    public GetAllUserResponseDto getAllUsers(GetAllUserRequestDto requestDto) {
        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));
        Long clinicId = securityUtils.getCurrentClinicId();
        Page<UserClinic> page =
                userClinicRepository.findAllByClinicIdAndDeletedFalse(clinicId, pageRequest);

        List<Long> userClinicIds = page.getContent().stream().map(UserClinic::getClinicId).toList();
        List<UserClinicRole> userClinicRoles = userClinicRoleRepository
                .findAllByUserClinicIds(userClinicIds);

        Map<Long, List<Role>> roleMap =
                userClinicRoles.stream().collect(Collectors.groupingBy((UserClinicRole item)
                                -> item.getUserClinic().getId(),
                        Collectors.mapping(UserClinicRole::getRole, Collectors.toList())));

        List<UserDto> users = page.getContent().stream().map(userClinic -> {
            UserDto dto = userMapper.toUserDto(userClinic.getUser());
            dto.setActive(userClinic.isActive());
            dto.setRoles(roleMap.getOrDefault(userClinic.getId(),
                    Collections.emptyList()).stream().map(roleMapper::toRoleDto).collect(Collectors.toSet()));

            return dto;
        }).toList();

        GetAllUserResponseDto response = new GetAllUserResponseDto();
        response.setResults(users);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
