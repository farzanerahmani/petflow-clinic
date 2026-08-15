package com.roochi.petflowpet.controller;


import com.roochi.petflowpet.dto.request.*;
import com.roochi.petflowpet.dto.response.*;
import com.roochi.petflowpet.facade.PetFacade;
import com.roochi.petflowpet.service.command.PetCommandService;
import com.roochi.petflowpet.service.query.PetQueryService;
import com.roochi.petflowshared.annotation.HttpServerException;
import com.roochi.petflowshared.annotation.Service;
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
 * @created 6/2/2026
 */
@RestController
@RequestMapping("api/pets")
@RequiredArgsConstructor
public class PetController implements PetFacade {

    private final PetCommandService commandPetService;
    private final PetQueryService petQueryService;


    @Override
    @Service(name = "addPet")
    @Operation(operationId = "addPet", description = "${PetController.addPet}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PetController.invalidRequest")
    public AddPetResponseDto addPet(@RequestBody AddPetRequestDto requestDto,
                                    @RequestHeader Map<String, Object> headers) {
        return commandPetService.addPet(requestDto);
    }

    @Override
    @Service(name = "getPet")
    @Operation(operationId = "getPet", description = "${PetController.getPet}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PetController.invalidRequest")
    public GetPetByIdResponseDto getPet(@RequestBody GetPetByIdRequestDto requestDto) {
        return petQueryService.getPetById(requestDto);
    }

    @Override
    @Service(name = "getAllPets")
    @Operation(operationId = "getAllPets", description = "${PetController.getAllPets}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PetController.invalidRequest")
    public GetAllPetsResponseDto getAllPets(@RequestBody GetAllPetsRequestDto requestDto,
                                            @RequestHeader Map<String, Object> headers) {
        return petQueryService.getAllPets(requestDto);
    }

    @Override
    @Service(name = "deletePet")
    @Operation(operationId = "deletePet", description = "${PetController.deletePet}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PetController.invalidRequest")
    public DeletePetResponseDto deletePet(@RequestBody DeletePetRequestDto requestDto,
                                          @RequestHeader Map<String, Object> headers) {
        return commandPetService.deletePet(requestDto);
    }

    @Override
    @Service(name = "updatePet")
    @Operation(operationId = "updatePet", description = "${PetController.updatePet}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PetController.invalidRequest")
    public UpdatePetResponseDto updatePet(@RequestBody UpdatePetRequestDto requestDto,
                                          @RequestHeader Map<String, Object> headers) {
        return commandPetService.updatePet(requestDto);
    }

    @Override
    @Service(name = "reportLostPet")
    @Operation(operationId = "reportLostPet", description = "${PetController.reportLostPet}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PetController.invalidRequest")
    public ReportLostPetResponseDto reportLostPet(@RequestBody ReportLostPetRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return commandPetService.reportLostPet(requestDto);
    }

    @Override
    @Service(name = "activatePet")
    @Operation(operationId = "activatePet", description = "${PetController.activatePet}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PetController.invalidRequest")
    public ActivatePetResponseDto activatePet(@RequestBody ActivatePetRequestDto requestDto,
                                              @RequestHeader Map<String, Object> headers) {
        return commandPetService.activatePet(requestDto);
    }

    @Override
    @Service(name = "deactivatePet")
    @Operation(operationId = "deactivatePet", description = "${PetController.deactivatePet}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PetController.invalidRequest")
    public DeactivatePetResponseDto deactivatePet(@RequestBody DeactivatePetRequestDto requestDto,
                                                  @RequestHeader Map<String, Object> headers) {
        return commandPetService.deactivatePet(requestDto);
    }

    @Override
    @Service(name = "reportDeceasedPet")
    @Operation(operationId = "reportDeceasedPet", description = "${PetController.reportDeceasedPet}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PetController.invalidRequest")
    public ReportDeceasedPetResponseDto reportDeceasedPet(@RequestBody ReportDeceasedPetRequestDto requestDto,
                                                          @RequestHeader Map<String, Object> headers) {
        return commandPetService.reportDeceasedPet(requestDto);
    }

    @Override
    @Service(name = "reportFoundPet")
    @Operation(operationId = "reportFoundPet", description = "${PetController.reportFoundPet}")
    @ApiResponse(
            responseCode = "400",
            content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
            description = "PetController.invalidRequest")
    public ReportFoundPetResponseDto reportFoundPet(@RequestBody ReportFoundPetRequestDto requestDto,
                                                    @RequestHeader Map<String, Object> headers) {
        return commandPetService.reportFoundPet(requestDto);
    }
}
