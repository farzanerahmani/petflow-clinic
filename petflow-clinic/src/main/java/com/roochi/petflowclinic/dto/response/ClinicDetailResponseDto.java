package com.roochi.petflowclinic.dto.response;

import com.roochi.petflowshared.enums.ClinicType;
import lombok.*;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClinicDetailResponseDto {
    private Long id;

    private String name;

    private String code;

    private ClinicType clinicType;

    private String description;

    private Boolean active;

    private List<ServiceTypeResponseDto> services;
}
