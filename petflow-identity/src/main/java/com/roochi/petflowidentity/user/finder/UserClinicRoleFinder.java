package com.roochi.petflowidentity.user.finder;

import com.roochi.petflowidentity.user.entity.Role;
import com.roochi.petflowidentity.user.entity.UserClinicRole;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public interface UserClinicRoleFinder {

    UserClinicRole findById(Long id);

    List<UserClinicRole> findByUserClinicId(Long userClinicId);

    List<Role>  findRoles(Long userClinicId);
}
