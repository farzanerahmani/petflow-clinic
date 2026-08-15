package com.roochi.petflowvisit.dto.request.labrequest;

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
public class UpdateLabRequestRequestDto {

    @NotNull
    private Long id;

    @NotNull
    private Long labTestId;

    @NotNull
    private LocalDate requestDate;

    private LocalDate sampleDate;

    private String note;
}
