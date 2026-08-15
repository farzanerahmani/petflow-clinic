package com.roochi.petflowdashboard.repository;

import com.roochi.petflowinventory.sale.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface SaleDashboardRepository
        extends JpaRepository<Sale, Long> {

    @Query("""
            select count(s)
            from Sale s
            where s.warehouse.clinicId = :clinicId
              and s.saleDate >= :from
              and s.saleDate <= :to
            """)
    long countSales(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

    @Query("""
            select coalesce(sum(s.totalAmount), 0)
            from Sale s
            where s.warehouse.clinicId = :clinicId
              and s.saleDate >= :from
              and s.saleDate <= :to
            """)
    java.math.BigDecimal sumSalesAmount(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

    @Query("""
            select s.saleDate,
                   count(s),
                   coalesce(sum(s.totalAmount), 0)
            from Sale s
            where s.warehouse.clinicId = :clinicId
              and s.saleDate >= :from
              and s.saleDate <= :to
            group by s.saleDate
            order by s.saleDate
            """)
    List<Object[]> getDailySales(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
}