package com.roochi.petflowshared.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author farzane.rahmani
 * @created 7/7/2026
 */
public class MobileValidator implements ConstraintValidator<Mobile,String> {
    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext context) {
        return mobile.matches("^09\\d{9}$");
    }
}
