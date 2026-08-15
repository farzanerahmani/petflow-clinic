package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.drug.*;
import com.roochi.petflowvisit.dto.request.procedure.*;
import com.roochi.petflowvisit.dto.response.drug.*;
import com.roochi.petflowvisit.dto.response.procedure.*;
import com.roochi.petflowvisit.procedure.facade.ProcedureFacade;
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
@RequestMapping("api/Procedure")
@RequiredArgsConstructor
public class ProcedureController {

    private final ProcedureFacade procedureFacade;

    @Service(name = "addProcedure")
    @Operation(operationId = "addProcedure", description = "${ProcedureController.addProcedure}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ProcedureController.invalidRequest")
    public AddProcedureResponseDto addProcedure(@RequestBody AddProcedureRequestDto requestDto,
                                                @RequestHeader Map<String, Object> headers) {
        return procedureFacade.addProcedure(requestDto);
    }

    @Service(name = "updateProcedure")
    @Operation(operationId = "updateProcedure", description = "${ProcedureController.updateProcedure}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ProcedureController.invalidRequest")
    public UpdateProcedureResponseDto updateProcedure(@RequestBody UpdateProcedureRequestDto requestDto,
                                                      @RequestHeader Map<String, Object> headers) {
        return procedureFacade.updateProcedure(requestDto);
    }

    @Service(name = "deleteProcedure")
    @Operation(operationId = "deleteProcedure", description = "${ProcedureController.deleteProcedure}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ProcedureController.invalidRequest")
    public DeleteProcedureResponseDto deleteProcedure(@RequestBody DeleteProcedureRequestDto requestDto,
                                                      @RequestHeader Map<String, Object> headers) {
        return procedureFacade.deleteProcedure(requestDto);
    }

    @Service(name = "getProcedureById")
    @Operation(operationId = "getProcedureById", description = "${ProcedureController.getProcedureById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ProcedureController.invalidRequest")
    public GetProcedureByIdResponseDto getProcedureById(@RequestBody GetProcedureByIdRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return procedureFacade.getProcedureById(requestDto);
    }

    @Service(name = "getProcedureForUpdate")
    @Operation(operationId = "getProcedureForUpdate", description = "${ProcedureController.getProcedureForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ProcedureController.invalidRequest")
    public GetProcedureForUpdateResponseDto getProcedureForUpdate(@RequestBody GetProcedureForUpdateRequestDto requestDto,
                                                                  @RequestHeader Map<String, Object> headers) {
        return procedureFacade.getProcedureForUpdate(requestDto);
    }

    @Service(name = "searchProcedure")
    @Operation(operationId = "searchProcedure", description = "${ProcedureController.searchProcedure}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "ProcedureController.invalidRequest")
    public SearchProcedureResponseDto searchProcedure(@RequestBody SearchProcedureRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return procedureFacade.searchProcedure(requestDto);
    }
}
