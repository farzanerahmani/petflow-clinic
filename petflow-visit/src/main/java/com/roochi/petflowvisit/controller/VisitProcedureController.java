package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.procedure.*;
import com.roochi.petflowvisit.dto.response.procedure.*;
import com.roochi.petflowvisit.procedure.facade.VisitProcedureFacade;
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
@RequestMapping("api/visit-procedure")
@RequiredArgsConstructor
public class VisitProcedureController {

    private final VisitProcedureFacade visitProcedureFacade;

    @Service(name = "addVisitProcedure")
    @Operation(operationId = "addVisitProcedure", description = "${VisitProcedureController.addVisitProcedure}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitProcedureController.invalidRequest")
    public AddVisitProcedureResponseDto addVisitProcedure(@RequestBody AddVisitProcedureRequestDto requestDto,
                                                @RequestHeader Map<String, Object> headers) {
        return visitProcedureFacade.addVisitProcedure(requestDto);
    }

    @Service(name = "updateVisitProcedure")
    @Operation(operationId = "updateVisitProcedure", description = "${VisitProcedureController.updateVisitProcedure}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitProcedureController.invalidRequest")
    public UpdateVisitProcedureResponseDto updateVisitProcedure(@RequestBody UpdateVisitProcedureRequestDto requestDto,
                                                      @RequestHeader Map<String, Object> headers) {
        return visitProcedureFacade.updateVisitProcedure(requestDto);
    }

    @Service(name = "deleteVisitProcedure")
    @Operation(operationId = "deleteVisitProcedure", description = "${VisitProcedureController.deleteVisitProcedure}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitProcedureController.invalidRequest")
    public DeleteVisitProcedureResponseDto deleteVisitProcedure(@RequestBody DeleteVisitProcedureRequestDto requestDto,
                                                      @RequestHeader Map<String, Object> headers) {
        return visitProcedureFacade.deleteVisitProcedure(requestDto);
    }

    @Service(name = "getVisitProcedureById")
    @Operation(operationId = "getVisitProcedureById", description = "${VisitProcedureController.getVisitProcedureById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitProcedureController.invalidRequest")
    public VisitProcedureResponseDto getVisitProcedureById(@RequestBody GetVisitProcedureByIdRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return visitProcedureFacade.getVisitProcedureById(requestDto);
    }

    @Service(name = "getVisitProcedureForUpdate")
    @Operation(operationId = "getVisitProcedureForUpdate", description = "${VisitProcedureController.getVisitProcedureForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitProcedureController.invalidRequest")
    public VisitProcedureResponseDto getVisitProcedureForUpdate(@RequestBody GetVisitProcedureForUpdateRequestDto requestDto,
                                                                  @RequestHeader Map<String, Object> headers) {
        return visitProcedureFacade.getVisitProcedureForUpdate(requestDto);
    }

    @Service(name = "searchVisitProcedure")
    @Operation(operationId = "searchVisitProcedure", description = "${VisitProcedureController.searchVisitProcedure}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "VisitProcedureController.invalidRequest")
    public SearchVisitProcedureResponseDto searchVisitProcedure(@RequestBody SearchVisitProcedureRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return visitProcedureFacade.searchVisitProcedure(requestDto);
    }
}
