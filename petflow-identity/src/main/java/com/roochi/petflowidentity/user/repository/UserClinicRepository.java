package com.roochi.petflowidentity.user.repository;

import com.roochi.petflowidentity.user.entity.UserClinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public interface UserClinicRepository extends JpaRepository<UserClinic, Long> {

    List<UserClinic> findAllByUserId(Long userId);

    Optional<UserClinic> findDefaultByUserId(Long userId);

    Optional<UserClinic> findByUserIdAndClinicId(Long userId, Long clinicId);

    boolean existsByUserId(Long userId);

    @EntityGraph(attributePaths = {"user"})
    Page<UserClinic> findAllByClinicIdAndDeletedFalse(Long clinicId, Pageable pageable);

    List<UserClinic> findAllActiveOrderByUserId(Long userId);

    List<UserClinic> findAllByUserIdAndDeletedFalse(Long userId);

    Optional<UserClinic> findByUserIdAndDefaultClinicTrueAndDeletedFalse(Long userId);



}
