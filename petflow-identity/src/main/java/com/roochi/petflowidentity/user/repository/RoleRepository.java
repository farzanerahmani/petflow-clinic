package com.roochi.petflowidentity.user.repository;

import com.roochi.petflowidentity.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByCode(String code);

    Optional<Role> findByTitle(String title);

    @Query(value = "SELECT utr.role " +
            "FROM UserClinicRole utr " +
            "WHERE utr.userClinic.id = :userClinicId")
    List<Role> findAllByUserClinicId(Long userClinicId);

    boolean existsByCode(String code);
}
