package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.drug.*;
import com.roochi.petflowvisit.dto.request.hospitalization.*;
import com.roochi.petflowvisit.dto.response.drug.*;
import com.roochi.petflowvisit.dto.response.hospitalization.*;
import com.roochi.petflowvisit.hospitalization.facade.HospitalizationFacade;
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
@RequestMapping("api/hospitalization")
@RequiredArgsConstructor
public class HospitalizationController {

    private final HospitalizationFacade hospitalizationFacade;

    @Service(name = "addHospitalization")
    @Operation(operationId = "addHospitalization", description = "${HospitalizationController.addHospitalization}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "HospitalizationController.invalidRequest")
    public AddHospitalizationResponseDto addHospitalization(@RequestBody AddHospitalizationRequestDto requestDto,
                                                            @RequestHeader Map<String, Object> headers) {
        return hospitalizationFacade.addHospitalization(requestDto);
    }

    @Service(name = "updateHospitalization")
    @Operation(operationId = "updateHospitalization", description = "${HospitalizationController.updateHospitalization}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "HospitalizationController.invalidRequest")
    public UpdateHospitalizationResponseDto updateHospitalization(@RequestBody UpdateHospitalizationRequestDto requestDto,
                                                                  @RequestHeader Map<String, Object> headers) {
        return hospitalizationFacade.updateHospitalization(requestDto);
    }

    @Service(name = "deleteHospitalization")
    @Operation(operationId = "deleteHospitalization", description = "${HospitalizationController.deleteHospitalization}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "HospitalizationController.invalidRequest")
    public DeleteHospitalizationResponseDto deleteHospitalization(@RequestBody DeleteHospitalizationRequestDto requestDto,
                                                                  @RequestHeader Map<String, Object> headers) {
        return hospitalizationFacade.deleteHospitalization(requestDto);
    }

    @Service(name = "getHospitalizationById")
    @Operation(operationId = "getHospitalizationById", description = "${DrugController.getHospitalizationById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "HospitalizationController.invalidRequest")
    public HospitalizationResponseDto getHospitalizationById(@RequestBody GetHospitalizationByIdRequestDto requestDto,
                                                             @RequestHeader Map<String, Object> headers) {
        return hospitalizationFacade.getHospitalizationById(requestDto);
    }

    @Service(name = "getHospitalizationForUpdate")
    @Operation(operationId = "getHospitalizationForUpdate", description = "${HospitalizationController.getHospitalizationForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "HospitalizationController.invalidRequest")
    public HospitalizationResponseDto getHospitalizationForUpdate(@RequestBody GetHospitalizationForUpdateRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return hospitalizationFacade.getHospitalizationForUpdate(requestDto);
    }

    @Service(name = "searchHospitalization")
    @Operation(operationId = "searchHospitalization", description = "${DrugController.searchHospitalization}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "HospitalizationController.invalidRequest")
    public SearchHospitalizationResponseDto searchHospitalization(@RequestBody SearchHospitalizationRequestDto requestDto,
                                                                  @RequestHeader Map<String, Object> headers) {
        return hospitalizationFacade.searchHospitalization(requestDto);
    }
}
