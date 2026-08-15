package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.labresult.*;
import com.roochi.petflowvisit.dto.response.labresult.*;
import com.roochi.petflowvisit.labresult.facade.LabResultFacade;
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
@RequestMapping("api/lab-result")
@RequiredArgsConstructor
public class LabResultController {

    private final LabResultFacade labResultFacade;

    @Service(name = "addLabResult")
    @Operation(operationId = "addLabResult", description = "${LabResultController.addLabResult}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabResultController.invalidRequest")
    public AddLabResultResponseDto addLabResult(@RequestBody AddLabResultRequestDto requestDto,
                                                @RequestHeader Map<String, Object> headers) {
        return labResultFacade.addLabResult(requestDto);
    }

    @Service(name = "updateLabResult")
    @Operation(operationId = "updateLabResult", description = "${LabResultController.updateLabResult}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabResultController.invalidRequest")
    public UpdateLabResultResponseDto updateLabResultt(@RequestBody UpdateLabResultRequestDto requestDto,
                                                       @RequestHeader Map<String, Object> headers) {
        return labResultFacade.updateLabResult(requestDto);
    }

    @Service(name = "deleteLabResult")
    @Operation(operationId = "deleteLabResult", description = "${LabResultController.deleteLabResult}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabResultController.invalidRequest")
    public DeleteLabResultResponseDto deleteLabResult(@RequestBody DeleteLabResultRequestDto requestDto,
                                                      @RequestHeader Map<String, Object> headers) {
        return labResultFacade.deleteLabResult(requestDto);
    }

    @Service(name = "getLabResultByLabRequestId")
    @Operation(operationId = "getLabResultByLabRequestId", description = "${LabResultController.getLabResultByLabRequestId}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabResultController.invalidRequest")
    public LabResultResponseDto getLabResultByLabRequestId(@RequestBody GetLabResultByLabRequestIdRequestDto requestDto,
                                                           @RequestHeader Map<String, Object> headers) {
        return labResultFacade.getLabResultByLabRequestId(requestDto);
    }

    @Service(name = "getLabResultForUpdate")
    @Operation(operationId = "getLabResultForUpdate", description = "${LabResultController.getLabResultForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabResultController.invalidRequest")
    public GetLabResultForUpdateResponseDto getLabTestForUpdate(@RequestBody GetLabResultForUpdateRequestDto requestDto,
                                                                @RequestHeader Map<String, Object> headers) {
        return labResultFacade.getLabResultForUpdate(requestDto);
    }

}
