package com.roochi.petflowinventory.adjustment.repository;

import com.roochi.petflowinventory.adjustment.entity.Adjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 8/1/2026
 */
@Repository
public interface AdjustmentRepository extends JpaRepository<Adjustment, Long> {

    Optional<Adjustment> findByAdjustmentNumber(
            String adjustmentNumber);

}
