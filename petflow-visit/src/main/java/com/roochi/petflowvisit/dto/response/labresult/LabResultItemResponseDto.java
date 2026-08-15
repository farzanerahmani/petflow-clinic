package com.roochi.petflowvisit.dto.response.labresult;

import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/21/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabResultItemResponseDto {

    private Long id;

    private Long labTestParameterId;

    private String parameterName;

    private String resultValue;

    private String unit;

    private String referenceRange;

    private String flag;

    private String note;
}
