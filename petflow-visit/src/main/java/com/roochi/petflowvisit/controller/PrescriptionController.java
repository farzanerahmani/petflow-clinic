package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.prescription.AddPrescriptionRequestDto;
import com.roochi.petflowvisit.dto.request.prescription.DeletePrescriptionRequestDto;
import com.roochi.petflowvisit.dto.request.prescription.GetPrescriptionByIdRequestDto;
import com.roochi.petflowvisit.dto.request.prescription.UpdatePrescriptionRequestDto;
import com.roochi.petflowvisit.dto.response.prescription.AddPrescriptionResponseDto;
import com.roochi.petflowvisit.dto.response.prescription.DeletePrescriptionResponseDto;
import com.roochi.petflowvisit.dto.response.prescription.GetPrescriptionByIdResponseDto;
import com.roochi.petflowvisit.dto.response.prescription.UpdatePrescriptionResponseDto;
import com.roochi.petflowvisit.prescription.service.command.PrescriptionCommandService;
import com.roochi.petflowvisit.prescription.service.query.PrescriptionQueryService;
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
 * @created 7/14/2026
 */
@RestController
@RequestMapping("api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionCommandService prescriptionCommandService;
    private final PrescriptionQueryService prescriptionQueryService;


    @Service(name = "addPrescription")
    @Operation(operationId = "addPrescription", description = "${PrescriptionController.addPrescription}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PrescriptionController.invalidRequest")
    public AddPrescriptionResponseDto addPrescription(@RequestBody AddPrescriptionRequestDto requestDto,
                                                      @RequestHeader Map<String, Object> headers) {
        return prescriptionCommandService.addPrescription(requestDto);
    }

    @Service(name = "updatePrescription")
    @Operation(operationId = "updatePrescription", description = "${PrescriptionController.updatePrescription}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PrescriptionController.invalidRequest")
    public UpdatePrescriptionResponseDto updatePrescription(@RequestBody UpdatePrescriptionRequestDto requestDto,
                                                            @RequestHeader Map<String, Object> headers) {
        return prescriptionCommandService.updatePrescription(requestDto);
    }

    @Service(name = "deletePrescription")
    @Operation(operationId = "deletePrescription", description = "${PrescriptionController.deletePrescription}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PrescriptionController.invalidRequest")
    public DeletePrescriptionResponseDto deletePrescription(@RequestBody DeletePrescriptionRequestDto requestDto,
                                                            @RequestHeader Map<String, Object> headers) {
        return prescriptionCommandService.deletePrescription(requestDto);
    }

    @Service(name = "getPrescriptionById")
    @Operation(operationId = "getPrescriptionById", description = "${PrescriptionController.getPrescriptionById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PrescriptionController.invalidRequest")
    public GetPrescriptionByIdResponseDto getPrescriptionById(@RequestBody GetPrescriptionByIdRequestDto requestDto,
                                                              @RequestHeader Map<String, Object> headers) {
        return prescriptionQueryService.getPrescriptionByVisitId(requestDto);
    }
}
