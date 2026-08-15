package com.roochi.petflowreport.dto.request;

import com.roochi.petflowvisit.visit.entity.enums.VisitStatus;
import com.roochi.petflowvisit.visit.entity.enums.VisitType;
import lombok.*;

import java.time.LocalDate;
/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitReportRequestDto {

    private LocalDate from;

    private LocalDate to;

    private Long doctorUserId;

    private Long petId;

    private VisitStatus status;

    private VisitType type;
}
