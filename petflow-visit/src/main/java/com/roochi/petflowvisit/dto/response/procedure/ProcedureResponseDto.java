package com.roochi.petflowvisit.dto.response.procedure;

import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/23/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcedureResponseDto {

    private Long id;

    private String code;

    private String name;

    private String description;

    private Boolean active;
}
