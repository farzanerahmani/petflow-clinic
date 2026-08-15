package com.roochi.petflowpet.service.query;

import com.roochi.petflowpet.dto.request.GetAllPetsRequestDto;
import com.roochi.petflowpet.dto.request.GetPetByIdRequestDto;
import com.roochi.petflowpet.dto.response.GetAllPetsResponseDto;
import com.roochi.petflowpet.dto.response.GetPetByIdResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/8/2026
 */
public interface PetQueryService {

    GetPetByIdResponseDto getPetById(GetPetByIdRequestDto requestDto);


    GetAllPetsResponseDto getAllPets(GetAllPetsRequestDto requestDto);

}
