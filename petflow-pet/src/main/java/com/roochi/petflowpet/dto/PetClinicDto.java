package com.roochi.petflowpet.dto;

import com.roochi.petflowpet.entity.enumeration.PetClinicStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/8/2026
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetClinicDto {
    private Long clinicId;
    private String clinicName;
    private LocalDate joinedAt;
    private LocalDate leftAt;
    private PetClinicStatus status;
}
