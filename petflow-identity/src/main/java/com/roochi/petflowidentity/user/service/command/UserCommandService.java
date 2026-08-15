package com.roochi.petflowidentity.user.service.command;

import com.roochi.petflowidentity.user.dto.role.AssignRoleRequestDto;
import com.roochi.petflowidentity.user.dto.role.AssignRoleResponseDto;
import com.roochi.petflowidentity.user.dto.user.*;

/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
public interface UserCommandService {

    AddUserResponseDto addUser(AddUserRequestDto requestDto) throws Exception;

    UpdateUserResponseDto updateUser(UpdateUserRequestDto requestDto) throws Exception;

    DeleteUserResponseDto deleteUser(DeleteUserRequestDto requestDto);

    AssignRoleResponseDto assignRoles(AssignRoleRequestDto requestDto);

    RemoveRoleResponseDto removeRole(RemoveRoleRequestDto requestDto) throws Exception;

    UserActiveResponseDto userActive(UserActiveRequestDto requestDto);

    UserDeactivateResponseDto userDeactivate(UserDeactivateRequestDto requestDto);
}
