package com.roochi.petflowinventory.purchase.repository;

import com.roochi.petflowinventory.purchase.entity.PurchaseItem;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/28/2026
 */


@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
    @Override
    @Query(""" 
            select pi from PurchaseItem pi where pi.id = :id 
            """)
    Optional<PurchaseItem> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(""" 
            select pi from PurchaseItem pi where pi.id = :id
             """)
    Optional<PurchaseItem> findByIdForUpdate(@Param("id") Long id);

    @Query(""" 
            select pi from PurchaseItem pi where pi.purchase.id = :purchaseId order by pi.id
             """)
    List<PurchaseItem> findAllByPurchaseId(@Param("purchaseId") Long purchaseId);

    @Query("""
            select pi from PurchaseItem pi 
            where (:purchaseId is null or pi.purchase.id = :purchaseId) 
            and (:drugId is null or pi.drug.id = :drugId) order by pi.id 
            """)
    Page<PurchaseItem> search(@Param("purchaseId") Long purchaseId,
                              @Param("drugId") Long drugId,
                              Pageable pageable);

    @Query(""" 
            select coalesce(sum(pi.lineTotal),0) from PurchaseItem pi where pi.purchase.id = :purchaseId 
            """)
    BigDecimal calculatePurchaseTotal(@Param("purchaseId") Long purchaseId);
}
