package com.roochi.petflowreport.repository;

import com.roochi.petflowinventory.purchase.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */
public interface PurchaseReportRepository extends JpaRepository<PurchaseItem, Long> {

    @Query("""
        select pi
        from PurchaseItem pi
        join fetch pi.purchase p
        join fetch pi.drug d
        join fetch p.supplier s
        where p.deleted = false
          and p.warehouse.clinicId = :clinicId
          and (:from is null or p.purchaseDate >= :from)
          and (:to is null or p.purchaseDate <= :to)
          and (:warehouseId is null or p.warehouse.id = :warehouseId)
          and (:drugId is null or d.id = :drugId)
        order by p.purchaseDate desc, p.purchaseNumber desc
        """)
    List<PurchaseItem> getPurchaseReport(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to,
            @Param("warehouseId") Long warehouseId,
            @Param("drugId") Long drugId
    );
}