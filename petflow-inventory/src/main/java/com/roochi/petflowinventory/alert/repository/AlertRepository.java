package com.roochi.petflowinventory.alert.repository;

import com.roochi.petflowinventory.alert.entity.Alert;
import com.roochi.petflowinventory.alert.entity.enums.AlertStatus;
import com.roochi.petflowinventory.alert.entity.enums.AlertType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


public interface AlertRepository
        extends JpaRepository<Alert, Long> {

    @Query("""
            select a
            from Alert a
            where a.id = :id
              and a.deleted = false
            """)
    Optional<Alert> findActiveById(
            @Param("id") Long id
    );

    @Query("""
            select a
            from Alert a
            where a.deleted = false
              and a.status = :status
            order by a.alertDate desc
            """)
    Page<Alert> findAllByStatus(
            @Param("status") AlertStatus status,
            Pageable pageable
    );

    boolean existsByStockIdAndTypeAndStatusAndDeletedFalse(
            Long stockId,
            AlertType type,
            AlertStatus status
    );
}
