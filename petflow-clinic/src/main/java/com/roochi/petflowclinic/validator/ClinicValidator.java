package com.roochi.petflowclinic.validator;

import com.roochi.petflowshared.exception.AlreadyExistsException;
import com.roochi.petflowshared.exception.DeactivateException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowshared.validator.Validator;
import com.roochi.petflowclinic.entity.Clinic;
import com.roochi.petflowclinic.repository.ClinicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Component
@RequiredArgsConstructor
public class ClinicValidator implements Validator<Clinic> {
    private final ClinicRepository repository;

    @Override
    public void validate(Clinic target) {
        ensureActive(target);
    }

    public void ensureCodeNotExists(String code) {
        if (repository.existsByCode(code)) {
            throw new AlreadyExistsException(ErrorCode.CLINIC_ALREADY_EXISTS);
        }
    }

    public void ensureActive(Clinic clinic) {
        if (Boolean.FALSE.equals(clinic.getActive()))
            throw new DeactivateException(ErrorCode.CLINIC_DEACTIVATE);
    }
}
