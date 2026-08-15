package com.roochi.petflowidentity.user.facade;


import com.roochi.petflowidentity.user.dto.role.AssignRoleRequestDto;
import com.roochi.petflowidentity.user.dto.role.AssignRoleResponseDto;
import com.roochi.petflowidentity.user.dto.user.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * @author farzane.rahmani
 * @created 6/13/2026
 */
public interface UserFacade {

    @PostMapping(value = "/updateUser",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UpdateUserResponseDto updateUser(@RequestBody UpdateUserRequestDto requestDto,
                                     @RequestHeader Map<String, Object> headers) throws Exception;

    @PostMapping(value = "/addUser",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    AddUserResponseDto addUser(@RequestBody AddUserRequestDto requestDto,
                               @RequestHeader Map<String, Object> headers) throws Exception;

    @PostMapping(value = "/getAllUsers",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    GetAllUserResponseDto getAllUsers(@RequestBody GetAllUserRequestDto requestDto,
                                      @RequestHeader Map<String, Object> headers);

    @PostMapping(value = "/deleteUser",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    DeleteUserResponseDto deleteUser(@RequestBody DeleteUserRequestDto requestDto,
                                     @RequestHeader Map<String, Object> headers);

    @PostMapping(value = "/assignRoles",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    AssignRoleResponseDto assignRoles(@RequestBody AssignRoleRequestDto requestDto,
                                      @RequestHeader Map<String, Object> headers);

    @PostMapping(value = "/removeRole",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    RemoveRoleResponseDto removeRole(@RequestBody RemoveRoleRequestDto requestDto,
                                     @RequestHeader Map<String, Object> headers) throws Exception;

    @PostMapping(value = "/userActive",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UserActiveResponseDto userActive(@RequestBody UserActiveRequestDto requestDto,
                                     @RequestHeader Map<String, Object> headers) throws Exception;


    @PostMapping(value = "/userDeactivate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UserDeactivateResponseDto userDeactivate(@RequestBody UserDeactivateRequestDto requestDto,
                                             @RequestHeader Map<String, Object> headers) throws Exception;

    @PostMapping(value = "/getUserById",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    GetUserByIdResponseDto getUserById(@RequestBody GetUserByIdRequestDto requestDto);

    @PostMapping(value = "/getUserByMobile",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    GetUserByMobileResponseDto getUserByMobile(@RequestBody GetUserByMobileRequestDto requestDto,
                                               @RequestHeader Map<String, Object> headers);

}
