package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.imaging.*;
import com.roochi.petflowvisit.dto.response.imaging.AddImagingResultResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.DeleteImagingResultResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.ImagingResultResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.UpdateImagingResultResponseDto;
import com.roochi.petflowvisit.imaging.facade.ImagingResultFacade;
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
@RequestMapping("api/imaging-result")
@RequiredArgsConstructor
public class ImagingResultController {

    private final ImagingResultFacade imagingResultFacade;

    @Service(name = "addIImagingResult")
    @Operation(operationId = "addImagingResult", description = "${ImagingResultController.addImagingResult}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingResultController.invalidRequest")
    public AddImagingResultResponseDto addImagingResult(@RequestBody AddImagingResultRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return imagingResultFacade.addImagingResult(requestDto);
    }

    @Service(name = "updateImagingResult")
    @Operation(operationId = "updateImagingResult", description = "${ImagingResultController.updateImagingResult}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingResultController.invalidRequest")
    public UpdateImagingResultResponseDto updateImagingResult(@RequestBody UpdateImagingResultRequestDto requestDto,
                                                              @RequestHeader Map<String, Object> headers) {
        return imagingResultFacade.updateImagingResult(requestDto);
    }

    @Service(name = "deleteImagingResult")
    @Operation(operationId = "deleteImagingResult", description = "${ImagingResultController.deleteImagingResult}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingResultController.invalidRequest")
    public DeleteImagingResultResponseDto deleteImagingResult(@RequestBody DeleteImagingResultRequestDto requestDto,
                                                              @RequestHeader Map<String, Object> headers) {
        return imagingResultFacade.deleteImagingResult(requestDto);
    }

    @Service(name = "getIImagingResultById")
    @Operation(operationId = "getIImagingResultById", description = "${ImagingResultController.getIImagingResulttById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingResultController.invalidRequest")
    public ImagingResultResponseDto getIImagingResultById(@RequestBody GetImagingResultByIdRequestDto requestDto,
                                                          @RequestHeader Map<String, Object> headers) {
        return imagingResultFacade.getIImagingResultById(requestDto);
    }

    @Service(name = "getImagingResultForUpdate")
    @Operation(operationId = "getImagingResultForUpdate", description = "${ImagingResultController.getImagingResultForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingResultController.invalidRequest")
    public ImagingResultResponseDto getImagingResultForUpdate(@RequestBody GetImagingResultForUpdateRequestDto requestDto,
                                                              @RequestHeader Map<String, Object> headers) {
        return imagingResultFacade.getImagingResultForUpdate(requestDto);
    }
}
