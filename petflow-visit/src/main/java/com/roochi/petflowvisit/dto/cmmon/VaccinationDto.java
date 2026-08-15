package com.roochi.petflowvisit.dto.cmmon;

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
public class VaccinationDto {

    private Long id;

    private String vaccineName;

    private LocalDate administrationDate;

    private LocalDate nextDueDate;
}