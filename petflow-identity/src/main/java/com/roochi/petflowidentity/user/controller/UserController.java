package com.roochi.petflowidentity.user.controller;

import com.roochi.petflowidentity.user.dto.role.AssignRoleRequestDto;
import com.roochi.petflowidentity.user.dto.role.AssignRoleResponseDto;
import com.roochi.petflowidentity.user.dto.user.*;
import com.roochi.petflowidentity.user.facade.UserFacade;
import com.roochi.petflowidentity.user.service.command.UserCommandService;
import com.roochi.petflowidentity.user.service.query.UserQueryService;
import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.PetFlowApiResponses;
import com.roochi.petflowshared.annotation.PetFlowInternalParameters;
import com.roochi.petflowshared.annotation.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author farzane.rahmani
 * @created 6/13/2026
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "${UserController.serviceNames}")
@PetFlowInternalParameters
@PetFlowApiResponses
@RequestMapping(path = "/user")
public class UserController implements UserFacade {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @Override
    @Service(name = "updateUser")
    @Operation(operationId = "updateUser", description = "${UserController.updateUser}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public UpdateUserResponseDto updateUser(@RequestBody UpdateUserRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) throws Exception {
        return userCommandService.updateUser(requestDto);
    }

    @Override
    @Service(name = "addUser")
    @Operation(operationId = "addUser", description = "${UserController.addUser}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public AddUserResponseDto addUser(@RequestBody AddUserRequestDto requestDto,
                                      @RequestHeader Map<String, Object> headers) throws Exception {
        return userCommandService.addUser(requestDto);
    }

    @Override
    @Service(name = "getAllUsers")
    @Operation(operationId = "getAllUsers", description = "${UserController.getAllUsers}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public GetAllUserResponseDto getAllUsers(@RequestBody GetAllUserRequestDto requestDto,
                                             @RequestHeader Map<String, Object> headers) {
        return userQueryService.getAllUsers(requestDto);
    }

    @Override
    @Service(name = "deleteUser")
    @Operation(operationId = "deleteUser", description = "${UserController.deleteUser}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public DeleteUserResponseDto deleteUser(@RequestBody DeleteUserRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return userCommandService.deleteUser(requestDto);
    }

    @Override
    @Service(name = "getUserById")
    @Operation(operationId = "getUserById", description = "${UserController.getUserById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public GetUserByIdResponseDto getUserById(@RequestBody GetUserByIdRequestDto requestDto) {
        return userQueryService.getUserById(requestDto);
    }

    @Override
    @Service(name = "getUserByMobile")
    @Operation(operationId = "getUserByMobile", description = "${UserController.getUserByMobile}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public GetUserByMobileResponseDto getUserByMobile(@RequestBody GetUserByMobileRequestDto requestDto,
                                                      @RequestHeader Map<String, Object> headers) {
        return userQueryService.getUserByMobile(requestDto);
    }

    @Override
    @Service(name = "assignRoles")
    @Operation(operationId = "assignRoles", description = "${UserController.assignRoles}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public AssignRoleResponseDto assignRoles(@RequestBody AssignRoleRequestDto requestDto,
                                             @RequestHeader Map<String, Object> headers) {
        return userCommandService.assignRoles(requestDto);
    }

    @Override
    @Service(name = "removeRole")
    @Operation(operationId = "removeRole", description = "${UserController.removeRole}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public RemoveRoleResponseDto removeRole(@RequestBody RemoveRoleRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) throws Exception {
        return userCommandService.removeRole(requestDto);
    }

    @Override
    @Service(name = "userActive")
    @Operation(operationId = "userActive", description = "${UserController.userActive}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public UserActiveResponseDto userActive(@RequestBody UserActiveRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) throws Exception {
        return userCommandService.userActive(requestDto);
    }

    @Override
    @Service(name = "userDeactivate")
    @Operation(operationId = "userDeactivate", description = "${UserController.userDeactivate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public UserDeactivateResponseDto userDeactivate(@RequestBody UserDeactivateRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) throws Exception {
        return userCommandService.userDeactivate(requestDto);
    }
}
