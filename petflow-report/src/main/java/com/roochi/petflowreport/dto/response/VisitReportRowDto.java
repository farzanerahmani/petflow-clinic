package com.roochi.petflowreport.dto.response;

import com.roochi.petflowvisit.visit.entity.enums.VisitStatus;
import com.roochi.petflowvisit.visit.entity.enums.VisitType;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitReportRowDto {

    private Long visitId;

    private Long petId;

    private Long doctorUserId;

    private LocalDateTime visitDate;

    private VisitStatus status;

    private VisitType type;

    private String chiefComplaint;

    private String diagnosis;

    private Double weight;

    private Double temperature;

    private Integer heartRate;

    private Integer respiratoryRate;

    private LocalDateTime finishedAt;
}
