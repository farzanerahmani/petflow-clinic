package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.drug.facade.DrugFacade;
import com.roochi.petflowvisit.dto.request.drug.*;
import com.roochi.petflowvisit.dto.response.drug.*;
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
@RequestMapping("api/drugs")
@RequiredArgsConstructor
public class DrugController {

    private final DrugFacade drugFacade;

    @Service(name = "addDrug")
    @Operation(operationId = "addDrug", description = "${DrugController.addDrug}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "DrugController.invalidRequest")
    public AddDrugResponseDto addDrug(@RequestBody AddDrugRequestDto requestDto,
                                      @RequestHeader Map<String, Object> headers) {
        return drugFacade.addDrug(requestDto);
    }

    @Service(name = "updateDrug")
    @Operation(operationId = "updateDrug", description = "${DrugController.updateDrug}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "DrugController.invalidRequest")
    public UpdateDrugResponseDto updateDrug(@RequestBody UpdateDrugRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return drugFacade.updateDrug(requestDto);
    }

    @Service(name = "deleteDrug")
    @Operation(operationId = "deleteDrug", description = "${DrugController.deleteDrug}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "DrugController.invalidRequest")
    public DeleteDrugResponseDto deleteDrug(@RequestBody DeleteDrugRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return drugFacade.deleteDrug(requestDto);
    }

    @Service(name = "getDrugById")
    @Operation(operationId = "getDrugById", description = "${DrugController.getDrugById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "DrugController.invalidRequest")
    public GetDrugByIdResponseDto getDrugById(@RequestBody GetDrugByIdRequestDto requestDto,
                                              @RequestHeader Map<String, Object> headers) {
        return drugFacade.getDrugById(requestDto);
    }

    @Service(name = "getDrugForUpdate")
    @Operation(operationId = "getDrugForUpdate", description = "${DrugController.getDrugForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "DrugController.invalidRequest")
    public GetDrugForUpdateResponseDto getDrugForUpdate(@RequestBody GetDrugForUpdateRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return drugFacade.getDrugForUpdate(requestDto);
    }

    @Service(name = "searchDrug")
    @Operation(operationId = "searchDrug", description = "${DrugController.searchDrug}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "DrugController.invalidRequest")
    public SearchDrugResponseDto searchDrug(@RequestBody SearchDrugRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return drugFacade.searchDrug(requestDto);
    }
}
