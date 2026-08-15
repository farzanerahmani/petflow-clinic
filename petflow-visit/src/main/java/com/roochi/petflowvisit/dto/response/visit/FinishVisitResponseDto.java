package com.roochi.petflowvisit.dto.response.visit;

import com.roochi.petflowvisit.dto.cmmon.VisitDto;
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
public class FinishVisitResponseDto {

    private VisitDto visit;
}
