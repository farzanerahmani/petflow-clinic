package com.roochi.petflowdashboard.repository;

import com.roochi.petflowinventory.sale.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface SaleItemDashboardRepository
        extends JpaRepository<SaleItem, Long> {

    @Query("""
            select
                si.drug.id,
                si.drug.brandName,
                coalesce(sum(si.quantity), 0),
                coalesce(sum(si.lineTotal), 0)
            from SaleItem si
            join si.sale s
            where s.warehouse.clinicId = :clinicId
              and s.saleDate >= :from
              and s.saleDate <= :to
            group by si.drug.id, si.drug.brandName
            order by sum(si.quantity) desc
            """)
    List<Object[]> findTopSellingDrugs(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
}
