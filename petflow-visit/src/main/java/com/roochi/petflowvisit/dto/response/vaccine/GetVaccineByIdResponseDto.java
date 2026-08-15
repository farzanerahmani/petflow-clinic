package com.roochi.petflowvisit.dto.response.vaccine;

import com.roochi.petflowvisit.dto.cmmon.VaccineDto;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetVaccineByIdResponseDto {

    private VaccineDto vaccine;
}
