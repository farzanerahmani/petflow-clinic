package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.medicalrecord.AddMedicalRecordRequestDto;
import com.roochi.petflowvisit.dto.request.medicalrecord.GetMedicalRecordByVisitIdRequestDto;
import com.roochi.petflowvisit.dto.request.medicalrecord.UpdateMedicalRecordRequestDto;
import com.roochi.petflowvisit.dto.response.medicalrecord.AddMedicalRecordResponseDto;
import com.roochi.petflowvisit.dto.response.medicalrecord.GetMedicalRecordByVisitIdResponseDto;
import com.roochi.petflowvisit.dto.response.medicalrecord.UpdateMedicalRecordResponseDto;
import com.roochi.petflowvisit.medicalrecord.service.command.MedicalRecordCommandService;
import com.roochi.petflowvisit.medicalrecord.service.query.MedicalRecordQueryService;
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
 * @created 7/11/2026
 */
@RestController
@RequestMapping("api/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordCommandService medicalRecordCommandService;
    private final MedicalRecordQueryService medicalRecordQueryService;

    @Service(name = "addMedicalRecord")
    @Operation(operationId = "addMedicalRecord", description = "${MedicalRecordController.addMedicalRecord}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitController.invalidRequest")
    public AddMedicalRecordResponseDto addMedicalRecord(@RequestBody AddMedicalRecordRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return medicalRecordCommandService.addMedicalRecord(requestDto);
    }

    @Service(name = "updateMedicalRecord")
    @Operation(operationId = "updateMedicalRecord", description = "${MedicalRecordController.updateMedicalRecord}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitController.invalidRequest")
    public UpdateMedicalRecordResponseDto updateMedicalRecord(@RequestBody UpdateMedicalRecordRequestDto requestDto,
                                                              @RequestHeader Map<String, Object> headers) {
        return medicalRecordCommandService.updateMedicalRecord(requestDto);
    }

    @Service(name = "getMedicalRecordByVisitId")
    @Operation(operationId = "getMedicalRecordByVisitId", description = "${MedicalRecordController.getMedicalRecordByVisitId}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitController.invalidRequest")
    public GetMedicalRecordByVisitIdResponseDto getMedicalRecordByVisitId(@RequestBody GetMedicalRecordByVisitIdRequestDto requestDto,
                                                                          @RequestHeader Map<String, Object> headers) {
        return medicalRecordQueryService.getMedicalRecordByVisitId(requestDto);
    }


}
