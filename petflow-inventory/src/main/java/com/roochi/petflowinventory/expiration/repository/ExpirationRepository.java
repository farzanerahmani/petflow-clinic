package com.roochi.petflowinventory.expiration.repository;

import com.roochi.petflowinventory.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
public interface ExpirationRepository extends JpaRepository<Stock, Long> {


    @Query("""
            select s
            from Stock s
            where s.deleted = false
              and s.active = true
              and s.expirationDate is not null
              and s.expirationDate < :date
            order by s.expirationDate asc
            """)
    List<Stock> findExpiredStocks(
            @Param("date") LocalDate date
    );


    @Query("""
            select s
            from Stock s
            where s.deleted = false
              and s.active = true
              and s.expirationDate is not null
              and s.expirationDate between :from and :to
            order by s.expirationDate asc
            """)
    List<Stock> findNearExpirationStocks(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
}
