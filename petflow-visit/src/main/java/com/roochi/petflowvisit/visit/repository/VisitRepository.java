package com.roochi.petflowvisit.visit.repository;

import com.roochi.petflowvisit.visit.entity.Visit;
import jakarta.persistence.LockModeType;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/10/2026
 */
@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> , JpaSpecificationExecutor<Visit> {
Optional<Visit> findByIdAndClinicId(Long id,Long clinicId);

@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query(value = "select v from Visit v where v.id =:visitId")
Optional<Visit> findByIdForUpdate(@Param("visitId") Long visitId);


}
