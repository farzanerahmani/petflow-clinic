package com.roochi.petflowvisit.visit.mapper;

import com.roochi.petflowidentity.user.dto.user.UserDto;
import com.roochi.petflowpet.dto.PetDto;
import com.roochi.petflowvisit.dto.cmmon.PetSummaryDto;
import com.roochi.petflowvisit.dto.cmmon.UserSummaryDto;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
public class VisitConvertor {

    public PetSummaryDto convertToPetSummaryDto(PetDto petDto){
        PetSummaryDto pet = new PetSummaryDto();
        pet.setBreed(petDto.getBreed());
        pet.setName(petDto.getName());
        pet.setId(petDto.getId());
        pet.setSpecies(petDto.getSpecies());
        pet.setStatus(petDto.getStatus());
        pet.setBrithDate(petDto.getBirthDate());
        return pet;

    }

    public UserSummaryDto convertToUserSummaryDto(UserDto userDto){
        UserSummaryDto user = new UserSummaryDto();
        user.setMobile(user.getMobile());
        user.setId(userDto.getId());
        return user;
    }
}
