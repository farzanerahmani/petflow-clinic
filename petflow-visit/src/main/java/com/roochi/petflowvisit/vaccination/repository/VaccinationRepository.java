package com.roochi.petflowvisit.vaccination.repository;

import com.roochi.petflowvisit.vaccination.entity.Vaccination;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    @Query("""
            select v
            from Vaccination v
            where v.id = :id
              and v.deleted = false
            """)
    Optional<Vaccination> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select v
            from Vaccination v
            where v.id = :id
              and v.deleted = false
            """)
    Optional<Vaccination> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select v
            from Vaccination v
            where v.visit.id = :visitId
              and v.deleted = false
            order by v.administrationDate desc
            """)
    Page<Vaccination> findByVisitId(@Param("visitId") Long visitId, Pageable pageable);

}
