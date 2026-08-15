package com.roochi.petflowclinic.finder;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowshared.finder.AbstractFinder;
import com.roochi.petflowclinic.entity.Clinic;
import com.roochi.petflowclinic.repository.ClinicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Component
@RequiredArgsConstructor
public class ClinicFinder extends AbstractFinder<Clinic,Long> {
    private final ClinicRepository repository;

    @Override
    protected JpaRepository<Clinic, Long> repository() {
        return repository;
    }

    @Override
    protected RuntimeException notFoundException(Long aLong) {
        return new NotFoundException(ErrorCode.CLINIC_NOT_FOUND);
    }

    public Clinic findByCode(String code){
        return repository.findByCode(code).orElseThrow(()->new NotFoundException(ErrorCode.CLINIC_NOT_FOUND));
    }


}
