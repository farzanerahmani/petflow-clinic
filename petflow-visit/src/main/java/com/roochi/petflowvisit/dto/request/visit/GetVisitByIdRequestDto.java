package com.roochi.petflowvisit.dto.request.visit;

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
public class GetVisitByIdRequestDto {

    @NonNull
    private Long visitId;
}
