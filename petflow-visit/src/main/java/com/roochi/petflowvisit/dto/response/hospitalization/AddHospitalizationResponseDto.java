package com.roochi.petflowvisit.dto.response.hospitalization;

import lombok.*;
import org.springframework.stereotype.Service;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddHospitalizationResponseDto {

    private Long visitId;
}
