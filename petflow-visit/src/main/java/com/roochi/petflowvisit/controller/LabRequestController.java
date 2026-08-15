package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.labrequest.*;
import com.roochi.petflowvisit.dto.response.labrequest.*;
import com.roochi.petflowvisit.labrequest.facade.LabRequestFacade;
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
@RequestMapping("api/lab-requst")
@RequiredArgsConstructor
public class LabRequestController {

    private final LabRequestFacade labRequestFacade;

    @Service(name = "addLabRequest")
    @Operation(operationId = "addLabRequest", description = "${LabRequestController.addLabRequest}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabRequestController.invalidRequest")
    public AddLabRequestResponseDto addLabRequest(@RequestBody AddLabRequestRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return labRequestFacade.addLabRequest(requestDto);
    }

    @Service(name = "updateLabRequest")
    @Operation(operationId = "updateLabRequest", description = "${LabRequestController.updateLabRequest}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabRequestController.invalidRequest")
    public UpdateLabRequestResponseDto updateLabRequest(@RequestBody UpdateLabRequestRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return labRequestFacade.updateLabRequest(requestDto);
    }

    @Service(name = "deleteLabRequest")
    @Operation(operationId = "deleteLabRequest", description = "${LabRequestController.deleteLabRequest}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabRequestController.invalidRequest")
    public DeleteLabRequestResponseDto deleteLabRequest(@RequestBody DeleteLabRequestRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return labRequestFacade.deleteLabRequest(requestDto);
    }

    @Service(name = "getLabRequestByVisitId")
    @Operation(operationId = "getLabRequestByVisitId", description = "${LabRequestController.getLabRequestByVisitId}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabRequestController.invalidRequest")
    public GetAllLabRequestByVisitIdResponseDto getLabRequestByVisitId(@RequestBody GetAllLabRequestByVisitIdRequestDto requestDto,
                                                                       @RequestHeader Map<String, Object> headers) {
        return labRequestFacade.getLabRequestByVisitId(requestDto);
    }

    @Service(name = "getLabRequestForUpdate")
    @Operation(operationId = "getLabRequestForUpdate", description = "${LabRequestController.getLabRequestForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabRequestController.invalidRequest")
    public GetLabRequestForUpdateResponseDto getLabTestForUpdate(@RequestBody GetLabRequestForUpdateRequestDto requestDto,
                                                                 @RequestHeader Map<String, Object> headers) {
        return labRequestFacade.getLabRequestForUpdate(requestDto);
    }

}
