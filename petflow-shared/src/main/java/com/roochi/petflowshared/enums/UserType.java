package com.roochi.petflowshared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Getter
@AllArgsConstructor
public enum UserType {

    SUPER_ADMIN("Super Admin"),
    ADMIN("Admin"),
    DOCTOR("Doctor"),
    RECEPTIONIST("Receptionist"),
    PET_OWNER("Pet Owner"),
    STAFF("Staff");

    private final String title;
}
