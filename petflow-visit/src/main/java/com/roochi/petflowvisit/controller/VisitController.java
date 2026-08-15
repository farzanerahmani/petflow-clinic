package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.visit.*;
import com.roochi.petflowvisit.dto.response.visit.*;
import com.roochi.petflowvisit.visit.service.command.VisitCommandService;
import com.roochi.petflowvisit.visit.service.query.VisitQueryService;
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
@RequestMapping("api/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitCommandService visitCommandService;
    private final VisitQueryService visitQueryService;


    @Service(name = "addVisit")
    @Operation(operationId = "addVisit", description = "${VisitController.addVisit}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitController.invalidRequest")
    public AddVisitResponseDto addVisit(@RequestBody AddVisitRequestDto requestDto,
                                        @RequestHeader Map<String, Object> headers) {
        return visitCommandService.addVisit(requestDto);
    }

    @Service(name = "getVisit")
    @Operation(operationId = "getVisit", description = "${VisitController.getVisit}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitController.invalidRequest")
    public GetVisitByIdResponseDto getVisit(@RequestBody GetVisitByIdRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return visitQueryService.getVisitById(requestDto);
    }

    @Service(name = "getAllVisits")
    @Operation(operationId = "getAllVisits", description = "${VisitController.getAllVisits}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitController.invalidRequest")
    public GetAllVisitsResponseDto getAllVisits(@RequestBody GetAllVisitsRequestDto requestDto,
                                                @RequestHeader Map<String, Object> headers) {
        return visitQueryService.getAllVisit(requestDto);
    }

    @Service(name = "cancelVisit")
    @Operation(operationId = "cancelVisit", description = "${VisitController.cancelVisit}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitController.invalidRequest")
    public CancelVisitResponseDto cancelVisit(@RequestBody CancelVisitRequestDto requestDto,
                                              @RequestHeader Map<String, Object> headers) {
        return visitCommandService.cancelVisit(requestDto);
    }

    @Service(name = "updateVisit")
    @Operation(operationId = "updateVisit", description = "${VisitController.updateVisit}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitController.invalidRequest")
    public UpdateVisitResponseDto updateVisit(@RequestBody UpdateVisitRequestDto requestDto,
                                              @RequestHeader Map<String, Object> headers) {
        return visitCommandService.updateVisit(requestDto);
    }

    @Service(name = "finishVisit")
    @Operation(operationId = "finishVisit", description = "${VisitController.finishVisit}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitController.invalidRequest")
    public FinishVisitResponseDto finishVisit(@RequestBody FinishVisitRequestDto requestDto,
                                              @RequestHeader Map<String, Object> headers) {
        return visitCommandService.finishVisit(requestDto);
    }

    @Service(name = "startVisit")
    @Operation(operationId = "startVisit", description = "${VisitController.startVisit}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitController.invalidRequest")
    public StartVisitResponseDto startVisit(@RequestBody StartVisitRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return visitCommandService.startVisit(requestDto);
    }
}
