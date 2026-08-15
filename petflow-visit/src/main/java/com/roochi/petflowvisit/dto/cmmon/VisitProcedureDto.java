package com.roochi.petflowvisit.dto.cmmon;

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
public class VisitProcedureDto {

    private Long id;

    private String procedureName;

    private LocalDate performedDate;

    private String performedByName;

    private BigDecimal cost;
}