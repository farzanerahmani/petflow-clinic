package com.roochi.petflowidentity.auth.dto.response;

import com.roochi.petflowshared.enums.ClinicType;
import lombok.Builder;
import lombok.Getter;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Getter
@Builder
public class ClinicSelectResponseDto {

    private Long userClinicId;
    private Long clinicId;
    private String clinicName;
    private ClinicType clinicType;
}
