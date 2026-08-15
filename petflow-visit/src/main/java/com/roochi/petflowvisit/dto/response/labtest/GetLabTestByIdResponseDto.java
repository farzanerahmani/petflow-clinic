package com.roochi.petflowvisit.dto.response.labtest;

import com.roochi.petflowvisit.dto.cmmon.DrugDto;
import com.roochi.petflowvisit.dto.cmmon.LabTestDto;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetLabTestByIdResponseDto {

    private LabTestDto labTest;
}
