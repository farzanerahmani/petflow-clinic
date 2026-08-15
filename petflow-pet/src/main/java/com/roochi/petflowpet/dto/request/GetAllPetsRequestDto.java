package com.roochi.petflowpet.dto.request;

import com.roochi.petflowpet.entity.enumeration.Gender;
import com.roochi.petflowpet.entity.enumeration.PetSpecies;
import com.roochi.petflowpet.entity.enumeration.PetStatus;
import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springdoc.core.converters.models.Pageable;

/**
 * @author farzane.rahmani
 * @created 6/2/2026
 */
@Schema(name = "GetAllPetsRequestDto")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class GetAllPetsRequestDto extends PageRequestDto {

    private String name;
    private String microchipId;
    private PetSpecies species;
    private String breed;
    private Long ownerId;
    private PetStatus status;
}
