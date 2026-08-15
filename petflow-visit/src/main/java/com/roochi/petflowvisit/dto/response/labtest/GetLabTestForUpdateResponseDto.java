package com.roochi.petflowvisit.dto.response.labtest;

import com.roochi.petflowvisit.dto.cmmon.LabTestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetLabTestForUpdateResponseDto {
    private LabTestDto labTest;
}
