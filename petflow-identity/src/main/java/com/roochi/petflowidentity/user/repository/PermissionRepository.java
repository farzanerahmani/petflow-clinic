package com.roochi.petflowidentity.user.repository;

import com.roochi.petflowidentity.user.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {

    Optional<Permission> findByCode(String code);

    boolean existsByCode(String code);
}
