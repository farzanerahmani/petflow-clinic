package com.roochi.petflowvisit.dto.request.visit;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/10/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinishVisitRequestDto {
    @NotNull
    private Long visitId;
}
