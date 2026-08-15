package com.roochi.petflowvisit.dto.request.hospitalization;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import com.roochi.petflowvisit.hospitalization.entity.enums.HospitalizationStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SearchHospitalizationRequestDto extends PageRequestDto {

    private Long visitId;

    private HospitalizationStatus status;

    private Long attendingVeterinarianId;

    private LocalDateTime admissionFrom;

    private LocalDateTime admissionTo;
}

