package com.roochi.petflowdashboard.repository;

import com.roochi.petflowinventory.purchase.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface PurchaseItemDashboardRepository
        extends JpaRepository<PurchaseItem, Long> {

    @Query("""
            select
                pi.drug.id,
                pi.drug.brandName,
                coalesce(sum(pi.quantity), 0),
                coalesce(sum(pi.lineTotal), 0)
            from PurchaseItem pi
            join pi.purchase p
            where p.warehouse.clinicId = :clinicId
              and p.purchaseDate >= :from
              and p.purchaseDate <= :to
            group by pi.drug.id, pi.drug.brandName
            order by sum(pi.quantity) desc
            """)
    List<Object[]> findTopPurchasedDrugs(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
}
