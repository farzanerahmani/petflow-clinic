package com.roochi.petflowvisit.dto.response.labresult;

import com.roochi.petflowvisit.dto.cmmon.LabResultDto;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetLabResultForUpdateResponseDto {

    private LabResultDto labResult;
}
