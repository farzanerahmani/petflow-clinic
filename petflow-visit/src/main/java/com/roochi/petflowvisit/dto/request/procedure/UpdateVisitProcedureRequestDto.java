package com.roochi.petflowvisit.dto.request.procedure;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateVisitProcedureRequestDto {

    @NotNull
    private Long id;

    @NotNull
    private Long procedureId;

    @NotNull
    private Long performedById;

    private Long assistantId;

    @NotNull
    private LocalDate performedDate;

    private Integer durationMinutes;

    @DecimalMin("0.0")
    private BigDecimal cost;

    @Size(max = 1000)
    private String note;
}
