package com.roochi.petflowvisit.dto.cmmon;

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
public class LabTestDto {

    private Long id;

    private String code;

    private String name;

    private Boolean active;
}


