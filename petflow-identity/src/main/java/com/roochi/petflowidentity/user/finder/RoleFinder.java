package com.roochi.petflowidentity.user.finder;

import com.roochi.petflowidentity.user.entity.Role;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public interface RoleFinder {
    Role findById(Long id);
    Role findByTitle(String title);

    Role findByCode(String code);
    List<Role> findAllByUserClinicId(Long userClinicId);
}
