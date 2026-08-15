package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.imaging.*;
import com.roochi.petflowvisit.dto.response.imaging.*;
import com.roochi.petflowvisit.imaging.facade.ImagingRequestFacade;
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
@RequestMapping("api/imaging-request")
@RequiredArgsConstructor
public class ImagingRequestController {

    private final ImagingRequestFacade imagingRequestFacade;

    @Service(name = "addIImagingRequest")
    @Operation(operationId = "addImagingRequest", description = "${ImagingRequestController.addImagingRequest}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingRequestController.invalidRequest")
    public AddImagingRequestResponseDto addImagingRequest(@RequestBody AddImagingRequestRequestDto requestDto,
                                                @RequestHeader Map<String, Object> headers) {
        return imagingRequestFacade.addImagingRequest(requestDto);
    }

    @Service(name = "updateImagingRequest")
    @Operation(operationId = "updateImagingRequest", description = "${ImagingRequestController.updateImagingRequest}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingRequestController.invalidRequest")
    public UpdateImagingRequestResponseDto updateImagingRequest(@RequestBody UpdateImagingRequestRequestDto requestDto,
                                                                @RequestHeader Map<String, Object> headers) {
        return imagingRequestFacade.updateImagingRequest(requestDto);
    }

    @Service(name = "deleteImagingRequest")
    @Operation(operationId = "deleteImagingRequest", description = "${ImagingRequestController.deleteImagingRequest}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingRequestController.invalidRequest")
    public DeleteImagingRequestResponseDto deleteImagingRequest(@RequestBody DeleteImagingRequestRequestDto requestDto,
                                                                @RequestHeader Map<String, Object> headers) {
        return imagingRequestFacade.deleteImagingRequest(requestDto);
    }

    @Service(name = "getIImagingRequestById")
    @Operation(operationId = "getIImagingRequestById", description = "${ImagingRequestController.getIImagingRequestById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingRequestController.invalidRequest")
    public ImagingRequestResponseDto getIImagingRequestById(@RequestBody GetImagingRequestByIdRequestDto requestDto,
                                                 @RequestHeader Map<String, Object> headers) {
        return imagingRequestFacade.getIImagingRequestById(requestDto);
    }

    @Service(name = "getImagingRequestForUpdate")
    @Operation(operationId = "getImagingRequestForUpdate", description = "${ImagingRequestController.getImagingRequestForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingRequestController.invalidRequest")
    public ImagingRequestResponseDto getImagingRequestForUpdate(@RequestBody GetImagingRequestForUpdateRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return imagingRequestFacade.getImagingRequestForUpdate(requestDto);
    }

    @Service(name = "searchImagingRequest")
    @Operation(operationId = "searchImagingRequest", description = "${ImagingRequestController.searchImagingRequest}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingRequestController.invalidRequest")
    public SearchImagingRequestResponseDto searchImagingRequest(@RequestBody SearchImagingRequestRequestDto requestDto,
                                                      @RequestHeader Map<String, Object> headers) {
        return imagingRequestFacade.searchImagingRequest(requestDto);
    }
}
