package com.roochi.petflowvisit.dto.response.vaccine;

import com.roochi.petflowvisit.dto.cmmon.VaccineDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetVaccineForUpdateResponseDto {

    private VaccineDto vaccine;
}
