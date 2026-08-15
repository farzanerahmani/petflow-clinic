package com.roochi.petflowvisit.dto.cmmon;

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
public class HospitalizationDto {

    private Long id;

    private LocalDateTime admissionDate;

    private LocalDateTime dischargeDate;

    private HospitalizationStatus status;

    private String ward;

    private String cageNumber;

    private String attendingVeterinarianName;
}
