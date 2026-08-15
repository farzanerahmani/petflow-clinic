package com.roochi.petflowvisit.dto.request.vaccine;

import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteVaccineRequestDto {
    private Long vaccineId;
}
