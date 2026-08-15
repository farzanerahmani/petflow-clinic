package com.roochi.petflowidentity.user.finder;

import com.roochi.petflowidentity.user.entity.Role;
import com.roochi.petflowidentity.user.entity.UserClinicRole;
import com.roochi.petflowidentity.user.repository.UserClinicRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Component
@RequiredArgsConstructor
public class UserClinicRoleFinderImpl implements UserClinicRoleFinder {

    private final UserClinicRoleRepository repository;

    @Override
    public UserClinicRole findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<UserClinicRole> findByUserClinicId(Long userClinicId) {
        return repository.findByUserClinicId(userClinicId);
    }

    @Override
    public List<Role> findRoles(Long userClinicId) {
        return repository.findByUserClinicId(userClinicId)
                .stream().map(UserClinicRole::getRole)
                .toList();
    }
}
