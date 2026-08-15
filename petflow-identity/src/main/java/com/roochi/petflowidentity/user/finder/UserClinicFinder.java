package com.roochi.petflowidentity.user.finder;

import com.roochi.petflowidentity.user.entity.UserClinic;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public interface UserClinicFinder {

    UserClinic findById(Long id);
    List<UserClinic> findByUserId(Long userId);
    UserClinic findDefaultByUserId(Long userId);

    UserClinic findByUserIdAndClinicId(Long userId, Long clinicId);
    List<UserClinic> findAllActiveByUserId(Long userId);
 }
