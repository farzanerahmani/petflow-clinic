package com.roochi.petflowvisit.dto.request.procedure;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@Schema(name = "SearchVisitProcedureRequestDto")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SearchVisitProcedureRequestDto extends PageRequestDto{

    private Long visitId;

    private Long procedureId;

    private Long performedById;

    private LocalDate fromDate;

    private LocalDate toDate;
}
