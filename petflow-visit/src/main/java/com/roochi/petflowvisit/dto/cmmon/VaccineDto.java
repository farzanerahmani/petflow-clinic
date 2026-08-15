package com.roochi.petflowvisit.dto.cmmon;

import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaccineDto {

    private Long id;

    private String code;

    private String name;

    private String disease;

    private Boolean active;
}
