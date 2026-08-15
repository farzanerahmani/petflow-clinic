package com.roochi.petflowvisit.dto.cmmon;

import com.roochi.petflowvisit.visit.entity.enums.VisitStatus;
import com.roochi.petflowvisit.visit.entity.enums.VisitType;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/10/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitDto {

    private Long id;

    private LocalDateTime visitDate;

    private String chiefComplaint;

    private String diagnosis;

    private String  description;

    private Double weight;

    private Double temperature;

    private Integer heartRate;

    private Integer respiratoryRate;

    private VisitStatus status;

    private VisitType type;

    private PetSummaryDto pet;

    private UserSummaryDto doctor;
}
