package com.roochi.petflowvisit.dto.response.procedure;

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
public class VisitProcedureResponseDto {

    private Long id;

    private Long visitId;

    private Long procedureId;

    private String procedureName;

    private Long performedById;

    private String performedByName;

    private Long assistantId;

    private String assistantName;

    private LocalDate performedDate;

    private Integer durationMinutes;

    private BigDecimal cost;

    private String note;
}
