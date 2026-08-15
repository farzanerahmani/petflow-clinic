package com.roochi.petflowvisit.dto.cmmon;

import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDto {

    private Long id;

    private VisitDto visit;
    private String history;
    private String clinicalFinding;
    private String diagnosis;
    private String treatmentPlan;
    private String recommendation;
    private String note;
}
