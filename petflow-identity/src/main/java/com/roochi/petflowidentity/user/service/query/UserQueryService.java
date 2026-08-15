package com.roochi.petflowidentity.user.service.query;

import com.roochi.petflowidentity.user.dto.user.*;

/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
public interface UserQueryService {

    GetUserByIdResponseDto getUserById(GetUserByIdRequestDto requestDto);

    GetUserByMobileResponseDto getUserByMobile(GetUserByMobileRequestDto requestDto);

    GetAllUserResponseDto getAllUsers(GetAllUserRequestDto requestDto);
}
