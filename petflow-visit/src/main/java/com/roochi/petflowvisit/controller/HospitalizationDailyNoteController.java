package com.roochi.petflowvisit.controller;

import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
import com.roochi.petflowvisit.dto.request.hospitalization.*;
import com.roochi.petflowvisit.dto.response.hospitalization.*;
import com.roochi.petflowvisit.hospitalization.facade.HospitalizationDailyNoteFacade;
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
@RequestMapping("api/hospitalization-daily-note")
@RequiredArgsConstructor
public class HospitalizationDailyNoteController {

    private final HospitalizationDailyNoteFacade hospitalizationDailyNoteFacade;

    @Service(name = "addHospitalizationDailyNote")
    @Operation(operationId = "addHospitalizationDailyNote", description = "${HospitalizationDailyNoteController.addHospitalizationDailyNote}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "HospitalizationDailyNoteController.invalidRequest")
    public AddHospitalizationDailyNoteResponseDto addHospitalizationDailyNote(@RequestBody AddHospitalizationDailyNoteRequestDto requestDto,
                                                            @RequestHeader Map<String, Object> headers) {
        return hospitalizationDailyNoteFacade.addHospitalizationDailyNote(requestDto);
    }

    @Service(name = "updateHospitalizationDailyNote")
    @Operation(operationId = "updateHospitalizationDailyNote", description = "${HospitalizationDailyNoteController.updateHospitalizationDailyNote}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "HospitalizationDailyNoteController.invalidRequest")
    public UpdateHospitalizationDailyNoteResponseDto updateHospitalizationDailyNote(@RequestBody UpdateHospitalizationDailyNoteRequestDto requestDto,
                                                                  @RequestHeader Map<String, Object> headers) {
        return hospitalizationDailyNoteFacade.updateHospitalizationDailyNote(requestDto);
    }

    @Service(name = "deleteHospitalizationDailyNote")
    @Operation(operationId = "deleteHospitalizationDailyNote", description = "${HospitalizationDailyNoteController.deleteHospitalizationDailyNote}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "HospitalizationDailyNoteController.invalidRequest")
    public DeleteHospitalizationDailyNoteResponseDto deleteHospitalizationDailyNote(@RequestBody DeleteHospitalizationDailyNoteRequestDto requestDto,
                                                                  @RequestHeader Map<String, Object> headers) {
        return hospitalizationDailyNoteFacade.deleteHospitalizationDailyNote(requestDto);
    }

    @Service(name = "getHospitalizationDailyNoteById")
    @Operation(operationId = "getHospitalizationDailyNoteById", description = "${HospitalizationDailyNoteController.getHospitalizationDailyNoteById}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "HospitalizationDailyNoteController.invalidRequest")
    public HospitalizationDailyNoteResponseDto getHospitalizationDailyNoteById(@RequestBody GetHospitalizationDailyNoteByIdRequestDto requestDto,
                                                             @RequestHeader Map<String, Object> headers) {
        return hospitalizationDailyNoteFacade.getHospitalizationDailyNoteById(requestDto);
    }

    @Service(name = "getHospitalizationDailyNoteForUpdate")
    @Operation(operationId = "getHospitalizationDailyNoteForUpdate", description = "${HospitalizationDailyNoteController.getHospitalizationDailyNoteForUpdate}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "HospitalizationDailyNoteController.invalidRequest")
    public HospitalizationDailyNoteResponseDto getHospitalizationDailyNoteForUpdate(@RequestBody GetHospitalizationDailyNoteForUpdateRequestDto requestDto,
                                                        @RequestHeader Map<String, Object> headers) {
        return hospitalizationDailyNoteFacade.getHospitalizationDailyNoteForUpdate(requestDto);
    }

    @Service(name = "searchHospitalizationDailyNote")
    @Operation(operationId = "searchHospitalizationDailyNote", description = "${HospitalizationDailyNoteController.searchHospitalizationDailyNote}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "HospitalizationDailyNoteController.invalidRequest")
    public SearchHospitalizationDailyNoteResponseDto searchHospitalizationDailyNote(@RequestBody SearchHospitalizationDailyNoteRequestDto requestDto,
                                                                  @RequestHeader Map<String, Object> headers) {
        return hospitalizationDailyNoteFacade.searchHospitalizationDailyNote(requestDto);
    }
}
