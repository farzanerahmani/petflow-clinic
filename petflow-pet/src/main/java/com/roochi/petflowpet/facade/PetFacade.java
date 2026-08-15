package com.roochi.petflowpet.facade;


import com.roochi.petflowpet.dto.request.*;
import com.roochi.petflowpet.dto.response.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * @author farzane.rahmani
 * @created 6/2/2026
 */
public interface PetFacade {

    @PostMapping(value = "/addPet",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    AddPetResponseDto addPet(@RequestBody AddPetRequestDto requestDto,
                             @RequestHeader Map<String, Object> headers);

    @PostMapping(value = "/getPet",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    GetPetByIdResponseDto getPet(@RequestBody GetPetByIdRequestDto requestDto);

    @PostMapping(value = "/getAllPets",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    GetAllPetsResponseDto getAllPets(@RequestBody GetAllPetsRequestDto requestDto,
                                     @RequestHeader Map<String, Object> headers);

    @PostMapping(value = "/deletePet",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    DeletePetResponseDto deletePet(@RequestBody DeletePetRequestDto requestDto,
                                   @RequestHeader Map<String, Object> headers);

    @PostMapping(value = "/updatePet",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UpdatePetResponseDto updatePet(@RequestBody UpdatePetRequestDto requestDto,
                                   @RequestHeader Map<String, Object> headers);

    @PostMapping(value = "/reportFoundPet",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ReportFoundPetResponseDto reportFoundPet(@RequestBody ReportFoundPetRequestDto requestDto,
                                   @RequestHeader Map<String, Object> headers);

    @PostMapping(value = "/ureportDeceasedPet",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ReportDeceasedPetResponseDto reportDeceasedPet(@RequestBody ReportDeceasedPetRequestDto requestDto,
                                   @RequestHeader Map<String, Object> headers);

    @PostMapping(value = "/reportLostPet",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ReportLostPetResponseDto reportLostPet(@RequestBody ReportLostPetRequestDto requestDto,
                                   @RequestHeader Map<String, Object> headers);

    @PostMapping(value = "/activatePet",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ActivatePetResponseDto activatePet(@RequestBody ActivatePetRequestDto requestDto,
                                           @RequestHeader Map<String, Object> headers);

    @PostMapping(value = "/deactivatePet",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    DeactivatePetResponseDto deactivatePet(@RequestBody DeactivatePetRequestDto requestDto,
                                           @RequestHeader Map<String, Object> headers);
}
