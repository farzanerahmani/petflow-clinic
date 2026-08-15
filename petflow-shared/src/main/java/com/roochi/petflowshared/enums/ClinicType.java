package com.roochi.petflowshared.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Getter
@RequiredArgsConstructor
public enum ClinicType {

    VETERINARY_CLINIC("Veterinary Clinic"),
    PET_SHOP("Pet Shop"),
    PHARMACY("Pharmacy"),
    GROOMING("Grooming"),
    TRAINING("Training"),
    PET_SITTER("Pet Sitter");

    private final String title;
}
