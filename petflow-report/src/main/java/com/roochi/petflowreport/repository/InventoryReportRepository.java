package com.roochi.petflowreport.repository;

import com.roochi.petflowinventory.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */


public interface InventoryReportRepository
        extends JpaRepository<Stock, Long> {

    @Query("""
        select s
        from Stock s
        join fetch s.warehouse w
        join fetch s.drug d
        where s.deleted = false
          and s.active = true
          and (:warehouseId is null or w.id = :warehouseId)
          and (:drugId is null or d.id = :drugId)
        order by d.brandName, s.expirationDate
        """)
    List<Stock> getInventoryReport(
            @Param("warehouseId") Long warehouseId,
            @Param("drugId") Long drugId
    );
}
