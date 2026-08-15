package com.roochi.petflowvisit.dto.cmmon;

import com.roochi.petflowvisit.prescription.entity.Prescription;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 7/12/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionItemDto {
    private Long id;

    @NotNull
    private Long drugId;

    private BigDecimal dosage;

    private Long doseUnitId;

    private Long frequencyId;

    private Integer duration;

    private Long durationUnitId;

    private String instruction;
}
