package com.roochi.petflowvisit.dto.request.visit;

import com.roochi.petflowvisit.visit.entity.enums.VisitType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/10/2026
 */
@Getter
@Setter
public class AddVisitRequestDto {

    @NotNull
    private Long petId;

    @NotNull
    private Long doctorUserId;

    @NotNull
    private LocalDateTime visitDate;

    @NotEmpty
    private String chiefComplaint;

    private String diagnosis;

    private String description;

    private Double weight;

    private Double temperature;

    private Integer heartRate;

    private Integer respiratoryRate;

    @NotNull
    private VisitType type;

    @NotNull
    private Long clinicId;

    private Long appointmentId;
}
