package com.roochi.petflowdashboard.repository;

import com.roochi.petflowinventory.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface InventoryDashboardRepository extends JpaRepository<Stock, Long> {

    @Query("""
            select count(s)
            from Stock s
            where s.active = true
              and s.warehouse.clinicId = :clinicId
              and s.quantity - s.reservedQuantity <= s.minimumQuantity
            """)
    long countLowStock(
            @Param("clinicId") Long clinicId
    );

    @Query("""
            select count(s)
            from Stock s
            where s.active = true
              and s.warehouse.clinicId = :clinicId
              and s.quantity - s.reservedQuantity <= 0
            """)
    long countOutOfStock(
            @Param("clinicId") Long clinicId
    );

    @Query("""
            select s
            from Stock s
            join fetch s.drug d
            join fetch s.warehouse w
            where w.clinicId = :clinicId
              and s.active = true
              and s.deleted = false
              and
                  (s.quantity - s.reservedQuantity)
                  <= s.minimumQuantity
            order by
                  (s.quantity - s.reservedQuantity) asc
            """)
    List<Stock> findLowStock(
            @Param("clinicId") Long clinicId
    );


    @Query("""
            select s
            from Stock s
            join fetch s.drug d
            join fetch s.warehouse w
            where w.clinicId = :clinicId
              and s.active = true
              and s.deleted = false
              and
                  (s.quantity - s.reservedQuantity)
                  <= 0
            order by s.drug.brandName
            """)
    List<Stock> findOutOfStock(
            @Param("clinicId") Long clinicId
    );


    @Query("""
            select s
            from Stock s
            join fetch s.drug d
            join fetch s.warehouse w
            where w.clinicId = :clinicId
              and s.active = true
              and s.deleted = false
              and s.expirationDate is not null
              and s.expirationDate >= :today
              and s.expirationDate <= :expirationDate
            order by s.expirationDate asc
            """)
    List<Stock> findExpiringSoon(
            @Param("clinicId") Long clinicId,
            @Param("today") LocalDate today,
            @Param("expirationDate") LocalDate expirationDate
    );
}