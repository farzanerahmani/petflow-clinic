package com.roochi.petflowvisit.dto.response.labrequest;

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
public class LabRequestResponseDto {

    private Long id;

    private Long visitId;

    private Long labTestId;

    private String labTestCode;

    private String labTestName;

    private LocalDate requestDate;

    private LocalDate sampleDate;

    private String note;
}
