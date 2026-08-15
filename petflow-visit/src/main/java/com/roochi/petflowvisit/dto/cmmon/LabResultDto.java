package com.roochi.petflowvisit.dto.cmmon;

import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/21/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabResultDto {

    private Long id;

    private LocalDate resultDate;

    private String report;
}
