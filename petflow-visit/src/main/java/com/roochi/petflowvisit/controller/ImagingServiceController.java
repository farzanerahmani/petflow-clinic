package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.drug.*;
import com.roochi.petflowvisit.dto.request.imaging.*;
import com.roochi.petflowvisit.dto.response.drug.*;
import com.roochi.petflowvisit.dto.response.imaging.*;
import com.roochi.petflowvisit.imaging.facade.ImagingServiceFacade;
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
@RequestMapping("api/imaging-service")
@RequiredArgsConstructor
public class ImagingServiceController {

    private final ImagingServiceFacade imagingServiceFacade;

    @Service(name = "addImagingService")
    @Operation(operationId = "addImagingService", description = "${ImagingServiceController.addImagingService}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingServiceController.invalidRequest")
    public AddImagingServiceResponseDto addImagingService(@RequestBody AddImagingServiceRequestDto requestDto,
                                                @RequestHeader Map<String, Object> headers) {
        return imagingServiceFacade.addImagingService(requestDto);
    }

    @Service(name = "updateImagingService")
    @Operation(operationId = "updateImagingService", description = "${ImagingServiceController.updateImagingService}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingServiceController.invalidRequest")
    public UpdateImagingServiceResponseDto updateImagingService(@RequestBody UpdateImagingServiceRequestDto requestDto,
                                                                @RequestHeader Map<String, Object> headers) {
        return imagingServiceFacade.updateImagingService(requestDto);
    }

    @Service(name = "deleteImagingService")
    @Operation(operationId = "deleteImagingService", description = "${ImagingServiceController.deleteImagingService}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "DrugController.invalidRequest")
    public DeleteImagingServiceResponseDto deleteImagingService(@RequestBody DeleteImagingServiceRequestDto requestDto,
                                                                @RequestHeader Map<String, Object> headers) {
        return imagingServiceFacade.deleteImagingService(requestDto);
    }

    @Service(name = "getImagingServiceById")
    @Operation(operationId = "getImagingServiceById", description = "${ImagingServiceController.getImagingServiceById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingServiceController.invalidRequest")
    public ImagingServiceResponseDto getDrugById(@RequestBody GetImagingServiceByIdRequestDto requestDto,
                                                 @RequestHeader Map<String, Object> headers) {
        return imagingServiceFacade.getImagingServiceById(requestDto);
    }

    @Service(name = "getImagingServiceForUpdate")
    @Operation(operationId = "getImagingServiceForUpdate", description = "${ImagingServiceController.getImagingServiceForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingServiceController.invalidRequest")
    public ImagingServiceResponseDto getImagingServiceForUpdate(@RequestBody GetImagingServiceForUpdateRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return imagingServiceFacade.getImagingServiceForUpdate(requestDto);
    }

    @Service(name = "searchImagingService")
    @Operation(operationId = "searchImagingService", description = "${ImagingServiceController.searchImagingService}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ImagingServiceController.invalidRequest")
    public SearchImagingServiceResponseDto searchDrug(@RequestBody SearchImagingServiceRequestDto requestDto,
                                                      @RequestHeader Map<String, Object> headers) {
        return imagingServiceFacade.searchImagingService(requestDto);
    }
}
