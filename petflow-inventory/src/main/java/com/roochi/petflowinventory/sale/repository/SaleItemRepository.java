package com.roochi.petflowinventory.sale.repository;

import com.roochi.petflowinventory.sale.entity.SaleItem;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */


@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select si
            from SaleItem si
            where si.id=:id
            """)
    Optional<SaleItem> findByIdForUpdate(
            @Param("id") Long id
    );

    @Query("""
            select si
            from SaleItem si
            where si.sale.id=:saleId
            order by si.id
            """)
    List<SaleItem> findAllBySaleId(
            @Param("saleId") Long saleId
    );

    @Query("""
            select si
            from SaleItem si
            where (:saleId is null or si.sale.id=:saleId)
              and (:drugId is null or si.drug.id=:drugId)
            order by si.id
            """)
    Page<SaleItem> search(
            @Param("saleId") Long saleId,
            @Param("drugId") Long drugId,
            Pageable pageable
    );

    @Query("""
            select coalesce(sum(si.lineTotal),0)
            from SaleItem si
            where si.sale.id=:saleId
            """)
    BigDecimal calculateSaleTotal(
            @Param("saleId") Long saleId
    );

}
