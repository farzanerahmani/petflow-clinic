package com.roochi.petflowpet.dto.request;

import com.roochi.petflowpet.entity.enumeration.Gender;
import com.roochi.petflowpet.entity.enumeration.PetSpecies;
import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import lombok.Data;
import org.springdoc.core.converters.models.Pageable;

/**
 * @author farzane.rahmani
 * @created 7/8/2026
 */
@Data
public class SearchPetRequestDto extends PageRequestDto {

    private String name;
    private String microchipId;
    private PetSpecies species;
    private Gender gender;
    private Long ownerId;
    private Pageable pageable;
}
