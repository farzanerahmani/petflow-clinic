package com.roochi.petflowreport.repository;

import com.roochi.petflowinventory.sale.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */
public interface SaleReportSummaryRepository
        extends JpaRepository<SaleItem, Long> {

    @Query("""
        select
            count(distinct s.id),
            sum(si.quantity),
            sum(si.lineTotal)
        from SaleItem si
        join si.sale s
        where s.deleted = false
          and s.warehouse.clinicId = :clinicId
          and (:from is null or s.saleDate >= :from)
          and (:to is null or s.saleDate <= :to)
          and (:warehouseId is null or s.warehouse.id = :warehouseId)
          and (:drugId is null or si.drug.id = :drugId)
        """)
    Object[] getSummary(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to,
            @Param("warehouseId") Long warehouseId,
            @Param("drugId") Long drugId
    );
}