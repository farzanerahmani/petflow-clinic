package com.roochi.petflowvisit.dto.cmmon;

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
public class LabResultItemDto {

    private Long id;

    private String parameterName;

    private String resultValue;

    private String unit;

    private String flag;
}
