package com.roochi.petflowpet.service.command;

import com.roochi.petflowpet.dto.request.*;
import com.roochi.petflowpet.dto.response.*;

/**
 * @author farzane.rahmani
 * @created 7/8/2026
 */
public interface PetCommandService {
    UpdatePetResponseDto updatePet(UpdatePetRequestDto requestDto);

    AddPetResponseDto addPet(AddPetRequestDto requestDto);

    DeletePetResponseDto deletePet(DeletePetRequestDto requestDto);

    ActivatePetResponseDto activatePet(ActivatePetRequestDto requestDto);

    DeactivatePetResponseDto deactivatePet(DeactivatePetRequestDto requestDto);

    ReportLostPetResponseDto reportLostPet(ReportLostPetRequestDto requestDto);

    ReportFoundPetResponseDto reportFoundPet(ReportFoundPetRequestDto requestDto);

    ReportDeceasedPetResponseDto reportDeceasedPet(ReportDeceasedPetRequestDto requestDto);



}
