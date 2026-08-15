package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.labtest.*;
import com.roochi.petflowvisit.dto.response.labtest.*;
import com.roochi.petflowvisit.labtest.facade.LabTestFacade;
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
@RequestMapping("api/labtests")
@RequiredArgsConstructor
public class LabTestController {

    private final LabTestFacade labTestFacade;

    @Service(name = "addLabTest")
    @Operation(operationId = "addLabTest", description = "${LabTestController.addLabTest}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabTestController.invalidRequest")
    public AddLabTestResponseDto addLabTest(@RequestBody AddLabTestRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return labTestFacade.addLabTest(requestDto);
    }

    @Service(name = "updateLabTest")
    @Operation(operationId = "updateLabTest", description = "${LabTestController.updateLabTest}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabTestController.invalidRequest")
    public UpdateLabTestResponseDto updateLabTest(@RequestBody UpdateLabTestRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return labTestFacade.updateLabTest(requestDto);
    }

    @Service(name = "deleteLabTest")
    @Operation(operationId = "deleteLabTest", description = "${LabTestController.deleteLabTest}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabTestController.invalidRequest")
    public DeleteLabTestResponseDto deleteLabTest(@RequestBody DeleteLabTestRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return labTestFacade.deleteLabTest(requestDto);
    }

    @Service(name = "getLabTestById")
    @Operation(operationId = "getLabTestById", description = "${LabTestController.getLabTestById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabTestController.invalidRequest")
    public GetLabTestByIdResponseDto getLabTestById(@RequestBody GetLabTestByIdRequestDto requestDto,
                                                    @RequestHeader Map<String, Object> headers) {
        return labTestFacade.getLabTestById(requestDto);
    }

    @Service(name = "getLabTestForUpdate")
    @Operation(operationId = "getLabTestForUpdate", description = "${LabTestController.getLabTestForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabTestController.invalidRequest")
    public GetLabTestForUpdateResponseDto getLabTestForUpdate(@RequestBody GetLabTestForUpdateRequestDto requestDto,
                                                              @RequestHeader Map<String, Object> headers) {
        return labTestFacade.getLabTestForUpdate(requestDto);
    }

    @Service(name = "searchLabTest")
    @Operation(operationId = "searchLabTest", description = "${LabTestController.searchLabTest}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "LabTestController.invalidRequest")
    public SearchLabTestResponseDto searchLabTest(@RequestBody SearchLabTestRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return labTestFacade.searchLabTest(requestDto);
    }
}
