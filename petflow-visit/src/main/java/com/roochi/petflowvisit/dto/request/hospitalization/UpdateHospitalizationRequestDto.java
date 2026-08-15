package com.roochi.petflowvisit.dto.request.hospitalization;

import com.roochi.petflowvisit.hospitalization.entity.enums.HospitalizationStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@Data
public class UpdateHospitalizationRequestDto {

    @NotNull
    private Long id;

    @NotNull
    private LocalDateTime admissionDate;

    private LocalDateTime dischargeDate;

    private String ward;

    private String cageNumber;

    @NotNull
    private HospitalizationStatus status;

    @NotNull
    private Long attendingVeterinarianId;

    @Size(max = 1000)
    private String diagnosis;

    @Size(max = 2000)
    private String treatmentPlan;

    @Size(max = 1000)
    private String dischargeSummary;

    @Size(max = 1000)
    private String note;


}
