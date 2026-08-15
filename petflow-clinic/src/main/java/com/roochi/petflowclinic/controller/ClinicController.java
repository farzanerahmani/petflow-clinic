package com.roochi.petflowclinic.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.PetFlowApiResponses;
import com.roochi.petflowshared.annotation.PetFlowInternalParameters;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowclinic.dto.request.*;
import com.roochi.petflowclinic.dto.response.*;
import com.roochi.petflowclinic.facade.ClinicFacade;
import com.roochi.petflowclinic.service.command.ClinicCommandService;
import com.roochi.petflowclinic.service.query.ClinicQueryService;
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

import java.util.List;
import java.util.Map;

/**
 * @author farzane.rahmani
 * @created 7/7/2026
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "${ClinicController.serviceNames}")
@PetFlowInternalParameters
@PetFlowApiResponses
@RequestMapping(path = "/clinics")
public class ClinicController implements ClinicFacade {

    private final ClinicCommandService clinicCommandService;
    private final ClinicQueryService clinicQueryService;

    @Override
    @Service(name = "createClinic")
    @Operation(operationId = "createClinic", description = "${ClinicController.createClinic}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public CreateClinicResponseDto createClinic(@RequestBody CreateClinicRequestDto requestDto,
                                                @RequestHeader Map<String, Object> headers) throws Exception {
        return clinicCommandService.create(requestDto);
    }

    @Override
    @Service(name = "updateClinic")
    @Operation(operationId = "updateClinic", description = "${ClinicController.updateClinic}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public UpdateClinicResponseDto updateClinic(@RequestBody UpdateClinicRequestDto requestDto,
                                                @RequestHeader Map<String, Object> headers) throws Exception {
        return clinicCommandService.update(requestDto);
    }

    @Override
    @Service(name = "deleteClinic")
    @Operation(operationId = "deleteClinic", description = "${ClinicController.deleteClinic}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public DeleteClinicResponseDto deleteClinic(@RequestBody DeleteClinicRequestDto requestDto,
                                                @RequestHeader Map<String, Object> headers) throws Exception {
        return clinicCommandService.delete(requestDto);
    }

    @Override
    @Service(name = "activateClinic")
    @Operation(operationId = "activateClinic", description = "${ClinicController.activateClinic}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public ActivateClinicResponseDto activateClinic(@RequestBody ActivateClinicRequestDto requestDto,
                                                    @RequestHeader Map<String, Object> headers) throws Exception {
        return clinicCommandService.activate(requestDto);
    }

    @Override
    @Service(name = "deactivateClinic")
    @Operation(operationId = "deactivateClinic", description = "${ClinicController.deactivateClinic}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public DeactivateClinicResponseDto deactivateClinic(@RequestBody DeactivateClinicRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) throws Exception {
        return clinicCommandService.deactivate(requestDto);
    }

    @Override
    @Service(name = "findClinicById")
    @Operation(operationId = "findClinicById", description = "${ClinicController.findClinicById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public ClinicResponseDto findClinicById(@RequestBody FindClinicByIdRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return clinicQueryService.findById(requestDto);
    }

    @Override
    @Service(name = "findClinicByCode")
    @Operation(operationId = "findClinicByCode", description = "${ClinicController.findClinicByCode}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public ClinicResponseDto findClinicByCode(@RequestBody FindClinicByCodeRequestDto requestDto,
                                              @RequestHeader Map<String, Object> headers) {
        return clinicQueryService.findByCode(requestDto);
    }

    @Override
    @Service(name = "findAllClinics")
    @Operation(operationId = "findAllClinics", description = "${ClinicController.findAllClinics}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "UserController.invalidRequest")
    public List<ClinicResponseDto> findAllClinics(@RequestBody FindAllClinicsRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return clinicQueryService.findAll(requestDto);
    }
}
