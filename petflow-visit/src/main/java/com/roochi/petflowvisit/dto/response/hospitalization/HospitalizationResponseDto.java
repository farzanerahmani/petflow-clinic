package com.roochi.petflowvisit.dto.response.hospitalization;

import com.roochi.petflowvisit.hospitalization.entity.enums.HospitalizationStatus;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalizationResponseDto {

    private Long id;

    private Long visitId;

    private LocalDateTime admissionDate;

    private LocalDateTime dischargeDate;

    private String ward;

    private String cageNumber;

    private HospitalizationStatus status;

    private Long attendingVeterinarianId;

    private String attendingVeterinarianName;

    private String diagnosis;

    private String treatmentPlan;

    private String dischargeSummary;

    private String note;
}
