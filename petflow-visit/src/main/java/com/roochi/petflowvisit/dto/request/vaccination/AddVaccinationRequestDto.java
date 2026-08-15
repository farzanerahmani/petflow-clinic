package com.roochi.petflowvisit.dto.request.vaccination;

import jakarta.validation.constraints.NotNull;
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
public class AddVaccinationRequestDto {

    @NotNull
    private Long visitId;

    @NotNull
    private Long vaccineId;

    @NotNull
    private LocalDate administrationDate;

    private LocalDate nextDueDate;

    private String batchNumber;

    private String administrationRoute;

    private String note;
}
