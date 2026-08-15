package com.roochi.petflowvisit.dto.response.vaccination;

import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaccinationResponseDto {

    private Long id;

    private Long visitId;

    private Long vaccineId;

    private String vaccineCode;

    private String vaccineName;

    private LocalDate administrationDate;

    private LocalDate nextDueDate;

    private String batchNumber;

    private String administrationRoute;

    private String note;
}
