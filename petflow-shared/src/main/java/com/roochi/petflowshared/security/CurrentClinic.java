package com.roochi.petflowshared.security;

import com.roochi.petflowshared.enums.ClinicType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Getter
@AllArgsConstructor
public class CurrentClinic {
    private final Long id;
    private final ClinicType clinicType;
}
