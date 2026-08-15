package com.roochi.petflowinventory.stock.repository;

import com.roochi.petflowinventory.stock.entity.Stock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, Long>,
        JpaSpecificationExecutor<Stock> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select s
            from Stock s
            where s.id = :stockId
            """)
    Optional<Stock> findByIdForUpdate(@Param("stockId") Long stockId);

    Optional<Stock> findByWarehouseIdAndDrugIdAndBatchNumberAndExpirationDateAndDeletedFalse(
            Long warehouseId,
            Long drugId,
            String batchNumber,
            LocalDate expirationDate
    );

    @Query("""
            select s
            from Stock s
            where s.deleted = false
              and s.active = true
              and s.quantity <= s.minimumQuantity
            order by s.drug.brandName
            """)
    List<Stock> findLowStocks();

    @Query("""
            select s
            from Stock s
            where s.deleted = false
              and s.active = true
              and s.expirationDate < :today
            order by s.expirationDate
            """)
    List<Stock> findExpiredStocks(
            @Param("today") LocalDate today);

    @Query("""
            select s
            from Stock s
            where s.deleted = false
              and s.active = true
              and s.expirationDate between :today and :until
            order by s.expirationDate
            """)
    List<Stock> findNearExpirationStocks(
            @Param("today") LocalDate today,
            @Param("until") LocalDate until);

    @Query("""
            select coalesce(sum(s.quantity),0)
            from Stock s
            where s.deleted = false
              and s.active = true
              and s.drug.id = :drugId
            """)
    BigDecimal calculateTotalQuantity(
            @Param("drugId") Long drugId);

    @Query("""
        select s
        from Stock s
        where s.warehouse.id = :warehouseId
          and s.drug.id = :drugId
          and s.active = true
          and s.deleted = false
          and s.quantity > s.reservedQuantity
        order by
            case when s.expirationDate is null then 1 else 0 end,
            s.expirationDate asc,
            s.batchNumber asc
        """)
    List<Stock> findAvailableStocksOrderByExpiration(
            @Param("warehouseId") Long warehouseId,
            @Param("drugId") Long drugId
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select s
        from Stock s
        where s.warehouse.id = :warehouseId
          and s.drug.id = :drugId
          and s.active = true
          and s.deleted = false
          and s.quantity > s.reservedQuantity
        order by
            case when s.expirationDate is null then 1 else 0 end,
            s.expirationDate asc,
            s.batchNumber asc
        """)
    List<Stock> findAvailableStocksOrderByExpirationForUpdate(
            @Param("warehouseId") Long warehouseId,
            @Param("drugId") Long drugId
    );

}