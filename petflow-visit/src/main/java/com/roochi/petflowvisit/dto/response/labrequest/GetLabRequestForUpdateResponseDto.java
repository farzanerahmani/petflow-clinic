package com.roochi.petflowvisit.dto.response.labrequest;

import com.roochi.petflowvisit.dto.cmmon.LabRequestDto;
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
public class GetLabRequestForUpdateResponseDto {

    private LabRequestDto labRequest;
}