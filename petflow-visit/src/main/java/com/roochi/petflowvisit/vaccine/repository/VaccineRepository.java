package com.roochi.petflowvisit.vaccine.repository;

import com.roochi.petflowvisit.vaccine.entity.Vaccine;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
public interface VaccineRepository extends JpaRepository<Vaccine, Long>, JpaSpecificationExecutor<Vaccine> {

    boolean existsByCode(String code);

    @Query(value = "select v " +
            "            from Vaccine v " +
            "            where v.id = :id " +
            "              and v.deleted = false")
    Optional<Vaccine> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = " select v from Vaccine v where v.id = :id and v.deleted = false")
    Optional<Vaccine> findByIdForUpdate(@Param("id") Long id);

}
