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
public class LabRequestDto {

    private Long id;

    private String labTestName;

    private LocalDate requestDate;

    private LocalDate sampleDate;
}
