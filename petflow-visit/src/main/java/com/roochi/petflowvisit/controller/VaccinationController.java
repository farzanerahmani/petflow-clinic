package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.vaccination.*;
import com.roochi.petflowvisit.dto.response.vaccination.*;
import com.roochi.petflowvisit.vaccination.facade.VaccinationFacade;
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
@RequestMapping("api/vaccinations")
@RequiredArgsConstructor
public class VaccinationController {

    private final VaccinationFacade vaccinationFacade;

    @Service(name = "addVaccination")
    @Operation(operationId = "addVaccination", description = "${VaccinationController.addVaccination}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VaccinationController.invalidRequest")
    public AddVaccinationResponseDto addVaccination(@RequestBody AddVaccinationRequestDto requestDto,
                                                    @RequestHeader Map<String, Object> headers) {
        return vaccinationFacade.addVaccination(requestDto);
    }

    @Service(name = "updateVaccination")
    @Operation(operationId = "updateVaccination", description = "${VaccinationController.updateVaccination}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VaccinationController.invalidRequest")
    public UpdateVaccinationResponseDto updateVaccination(@RequestBody UpdateVaccinationRequestDto requestDto,
                                                          @RequestHeader Map<String, Object> headers) {
        return vaccinationFacade.updateVaccination(requestDto);
    }

    @Service(name = "deleteVaccination")
    @Operation(operationId = "deleteVaccination", description = "${VaccinationController.deleteVaccination}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VVaccinationController.invalidRequest")
    public DeleteVaccinationResponseDto deleteVaccination(@RequestBody DeleteVaccinationRequestDto requestDto,
                                                          @RequestHeader Map<String, Object> headers) {
        return vaccinationFacade.deleteVaccination(requestDto);
    }

    @Service(name = "getVaccinationByVisitId")
    @Operation(operationId = "getVaccinationByVisitId", description = "${VaccinationController.getVaccinationByVisitId}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VaccinationController.invalidRequest")
    public GetAllVaccinationByVisitIdResponseDto getVaccinationByVisitId(@RequestBody GetAllVaccinationByVisitIdRequestDto requestDto,
                                                                         @RequestHeader Map<String, Object> headers) {
        return vaccinationFacade.getVaccinationByVisitId(requestDto);
    }

    @Service(name = "getVaccinationForUpdate")
    @Operation(operationId = "getVaccinationForUpdate", description = "${VaccinationController.getVaccinationForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VaccinationController.invalidRequest")
    public GetVaccinationForUpdateResponseDto getVaccinationForUpdate(@RequestBody GetVaccinationForUpdateRequestDto requestDto,
                                                                      @RequestHeader Map<String, Object> headers) {
        return vaccinationFacade.getVaccinationForUpdate(requestDto);
    }
}