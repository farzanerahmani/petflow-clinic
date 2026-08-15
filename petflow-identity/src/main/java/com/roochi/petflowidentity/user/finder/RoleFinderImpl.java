package com.roochi.petflowidentity.user.finder;

import com.roochi.petflowidentity.user.entity.Role;
import com.roochi.petflowidentity.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Component
@RequiredArgsConstructor
public class RoleFinderImpl implements RoleFinder{
    private final RoleRepository repository;

    @Override
    public Role findById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->new IllegalArgumentException());
    }

    @Override
    public Role findByTitle(String title) {
        return repository.findByTitle(title).orElseThrow();
    }

    @Override
    public Role findByCode(String code){
        return repository.findByCode(code).orElseThrow();
    }

    @Override
    public List<Role> findAllByUserClinicId(Long userClinicId) {
        return repository.findAllByUserClinicId(userClinicId);
    }
}
