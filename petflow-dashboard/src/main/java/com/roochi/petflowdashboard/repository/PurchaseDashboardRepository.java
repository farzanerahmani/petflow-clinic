package com.roochi.petflowdashboard.repository;

import com.roochi.petflowinventory.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface PurchaseDashboardRepository
        extends JpaRepository<Purchase, Long> {

    @Query("""
            select count(p)
            from Purchase p
            where p.warehouse.clinicId = :clinicId
              and p.purchaseDate >= :from
              and p.purchaseDate <= :to
            """)
    long countPurchases(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

    @Query("""
            select coalesce(sum(p.totalAmount), 0)
            from Purchase p
            where p.warehouse.clinicId = :clinicId
              and p.purchaseDate >= :from
              and p.purchaseDate <= :to
            """)
    java.math.BigDecimal sumPurchaseAmount(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

    @Query("""
            select p.purchaseDate,
                   count(p),
                   coalesce(sum(p.totalAmount), 0)
            from Purchase p
            where p.warehouse.clinicId = :clinicId
              and p.purchaseDate >= :from
              and p.purchaseDate <= :to
            group by p.purchaseDate
            order by p.purchaseDate
            """)
    List<Object[]> getDailyPurchases(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
}