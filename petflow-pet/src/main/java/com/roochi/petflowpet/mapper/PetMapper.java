package com.roochi.petflowpet.mapper;

import com.roochi.petflowpet.dto.PetDto;
import com.roochi.petflowpet.dto.request.AddPetRequestDto;
import com.roochi.petflowpet.dto.request.UpdatePetRequestDto;
import com.roochi.petflowpet.entity.Pet;
import com.roochi.petflowshared.mapper.BaseMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author farzane.rahmani
 * @created 7/8/2026
 */
@Mapper(componentModel = "spring")
public interface PetMapper extends BaseMapper<Pet, PetDto> {

    Pet toEntity(AddPetRequestDto requestDto);

    Pet toEntity(UpdatePetRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdatePetRequestDto requestDto,
                @MappingTarget Pet pet);

    PetDto toPetDto(Pet pet);
}