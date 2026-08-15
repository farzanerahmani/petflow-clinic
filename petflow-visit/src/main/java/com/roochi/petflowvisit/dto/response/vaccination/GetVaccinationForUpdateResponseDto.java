package com.roochi.petflowvisit.dto.response.vaccination;

import com.roochi.petflowvisit.dto.cmmon.VaccinationDto;
import com.roochi.petflowvisit.dto.cmmon.VaccineDto;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetVaccinationForUpdateResponseDto {

    private VaccinationDto vaccination;
}
