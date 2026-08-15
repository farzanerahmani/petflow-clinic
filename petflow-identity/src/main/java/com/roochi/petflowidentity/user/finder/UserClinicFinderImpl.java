package com.roochi.petflowidentity.user.finder;

import com.roochi.petflowidentity.user.entity.UserClinic;
import com.roochi.petflowidentity.user.repository.UserClinicRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Component
@RequiredArgsConstructor
public class UserClinicFinderImpl implements UserClinicFinder {
    private final UserClinicRepository repository;

    @Override
    public UserClinic findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public List<UserClinic> findByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public UserClinic findDefaultByUserId(Long userId) {
        return repository.findDefaultByUserId(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public UserClinic findByUserIdAndClinicId(Long userId, Long clinicId){
        return repository.findByUserIdAndClinicId(userId,clinicId)
                .orElseThrow(()->new NotFoundException(ErrorCode.CLINIC_NOT_FOUND));
    }

    @Override
    public List<UserClinic> findAllActiveByUserId(Long userId) {
        return repository.findAllActiveOrderByUserId(userId);
    }
}
