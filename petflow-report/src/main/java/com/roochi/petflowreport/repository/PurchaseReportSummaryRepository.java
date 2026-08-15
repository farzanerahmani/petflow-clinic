package com.roochi.petflowreport.repository;

import com.roochi.petflowinventory.purchase.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */
public interface PurchaseReportSummaryRepository
        extends JpaRepository<PurchaseItem, Long> {

    @Query("""
        select
            count(distinct p.id),
            sum(pi.quantity),
            sum(pi.lineTotal)
        from PurchaseItem pi
        join pi.purchase p
        where p.deleted = false
          and p.warehouse.clinicId = :clinicId
          and (:from is null or p.purchaseDate >= :from)
          and (:to is null or p.purchaseDate <= :to)
          and (:warehouseId is null or p.warehouse.id = :warehouseId)
          and (:drugId is null or pi.drug.id = :drugId)
        """)
    Object[] getSummary(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to,
            @Param("warehouseId") Long warehouseId,
            @Param("drugId") Long drugId
    );
}