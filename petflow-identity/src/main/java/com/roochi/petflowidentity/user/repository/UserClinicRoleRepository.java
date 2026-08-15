package com.roochi.petflowidentity.user.repository;

import com.roochi.petflowidentity.user.entity.Role;
import com.roochi.petflowidentity.user.entity.UserClinicRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Repository
public interface UserClinicRoleRepository extends JpaRepository<UserClinicRole, Long> {

    List<UserClinicRole> findByUserClinicId(Long userClinicId);

    Optional<UserClinicRole> findById(Long userId);

    void deleteByUserClinicId(Long userClinicId);

    Optional<UserClinicRole> findByUserClinicIdAndRole(Long userClinicId, Role role);

    @Query(value = "SELECT utr FROM UserClinicRole  utr " +
            "JOIN FETCH utr.role WHERE utr.userClinic.id in (:userClinicIds)")
    List<UserClinicRole> findAllByUserClinicIds(List<Long> userClinicIds);

    List<UserClinicRole> findAllByUserClinicId(Long userClinicId);

}
