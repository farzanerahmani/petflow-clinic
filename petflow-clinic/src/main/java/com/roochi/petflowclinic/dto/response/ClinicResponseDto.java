package com.roochi.petflowclinic.dto.response;

import com.roochi.petflowshared.enums.ClinicType;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClinicResponseDto {

    private Long id;

    private String name;

    private String code;

    private ClinicType clinicType;

    private String description;

    private Boolean active;
}
