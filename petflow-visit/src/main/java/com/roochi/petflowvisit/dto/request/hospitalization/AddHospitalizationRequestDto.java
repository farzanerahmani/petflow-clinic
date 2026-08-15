package com.roochi.petflowvisit.dto.request.hospitalization;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class AddHospitalizationRequestDto {

    @NotNull
    private Long visitId;

    @NotNull
    private LocalDateTime admissionDate;

    private String ward;

    private String cageNumber;

    @NotNull
    private Long attendingVeterinarianId;

    @Size(max = 1000)
    private String diagnosis;

    @Size(max = 2000)
    private String treatmentPlan;

    @Size(max = 1000)
    private String note;
}
