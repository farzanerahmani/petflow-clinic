package com.roochi.petflowvisit.dto.cmmon;

import com.roochi.petflowpet.entity.enumeration.PetSpecies;
import com.roochi.petflowpet.entity.enumeration.PetStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetSummaryDto {

    private Long id;
    private String name;
    private PetSpecies species;
    private String breed;
    private LocalDate brithDate;
    private PetStatus status;
}
