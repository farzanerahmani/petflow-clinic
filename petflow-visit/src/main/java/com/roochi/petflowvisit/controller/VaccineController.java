package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.vaccine.*;
import com.roochi.petflowvisit.dto.response.vaccine.*;
import com.roochi.petflowvisit.vaccine.facade.VaccineFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@RestController
@RequestMapping("api/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final VaccineFacade vaccineFacade;

    @Service(name = "addVaccine")
    @Operation(operationId = "addVaccine", description = "${VaccineController.addVaccine}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VaccineController.invalidRequest")
    public AddVaccineResponseDto addVaccine(@RequestBody AddVaccineRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return vaccineFacade.addVaccine(requestDto);
    }

    @Service(name = "updateVaccine")
    @Operation(operationId = "updateVaccine", description = "${VaccineController.updateVaccine}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VaccineController.invalidRequest")
    public UpdateVaccineResponseDto updateVaccine(@RequestBody UpdateVaccineRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return vaccineFacade.updateVaccine(requestDto);
    }

    @Service(name = "deleteVaccine")
    @Operation(operationId = "deleteVaccine", description = "${VaccineController.deleteVaccine}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VaccineController.invalidRequest")
    public DeleteVaccineResponseDto deleteVaccine(@RequestBody DeleteVaccineRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return vaccineFacade.deleteVaccine(requestDto);
    }

    @Service(name = "getVaccineById")
    @Operation(operationId = "getVaccineById", description = "${VaccineController.getVaccineById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VaccineController.invalidRequest")
    public GetVaccineByIdResponseDto getVaccineById(@RequestBody GetVaccineByIdRequestDto requestDto,
                                                    @RequestHeader Map<String, Object> headers) {
        return vaccineFacade.getVaccineById(requestDto);
    }

    @Service(name = "getVaccineForUpdate")
    @Operation(operationId = "getVaccineForUpdate", description = "${VaccineController.getVaccineForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VaccineController.invalidRequest")
    public GetVaccineForUpdateResponseDto getVaccineForUpdate(@RequestBody GetVaccineForUpdateRequestDto requestDto,
                                                              @RequestHeader Map<String, Object> headers) {
        return vaccineFacade.getVaccineForUpdate(requestDto);
    }

    @Service(name = "searchVaccine")
    @Operation(operationId = "searchVaccine", description = "${VaccineController.searchVaccine}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VaccineController.invalidRequest")
    public SearchVaccineResponseDto searchVaccine(@RequestBody SearchVaccineRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return vaccineFacade.searchVaccine(requestDto);
    }
}
