package com.roochi.petflowpet.dto;


import com.roochi.petflowpet.entity.enumeration.Gender;
import com.roochi.petflowpet.entity.enumeration.PetSpecies;
import com.roochi.petflowpet.entity.enumeration.PetStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 6/2/2026
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {
    private Long id;
    private Long ownerId;
    private String name;
    private PetSpecies species;
    private String breed;
    private Gender gender;
    private BigDecimal weight;
    private LocalDate birthDate;
    private String microchipId;
    private PetStatus status;
    private List<PetClinicDto> petClinics;
}
