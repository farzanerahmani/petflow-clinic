package com.roochi.petflowvisit.dto.response.hospitalization;

import com.roochi.petflowvisit.hospitalization.entity.enums.HospitalizationStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateHospitalizationResponseDto {

    private Long id;
}
