package com.roochi.petflowvisit.drug.repository;

import com.roochi.petflowvisit.drug.entity.Drug;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
public interface DrugRepository extends JpaRepository<Drug, Long>, JpaSpecificationExecutor<Drug> {

    Boolean existsByCode(String code);

    @Query(value = "select d from Drug d where d.id =:drugId and d.deleted=false ")
    Optional<Drug> findById(@Param("drugId") Long drugId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select d from Drug d where d.id =:drugId and d.deleted=false ")
    Optional<Drug> findByIdForUpdate(@Param("drugId") Long drugId);
}
