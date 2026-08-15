package com.roochi.petflowvisit.dto.cmmon;

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
public class ProcedureDto {

    private Long id;

    private String code;

    private String name;
}
