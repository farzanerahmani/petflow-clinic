package com.roochi.petflowinventory.purchase.repository;

import com.roochi.petflowinventory.purchase.entity.Purchase;
import com.roochi.petflowinventory.purchase.entity.enums.PurchaseStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */


@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Override
    @Query(""" 
            select p from Purchase p where p.id = :id and p.deleted = false
             """)
    Optional<Purchase> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(""" 
            select p from Purchase p where p.id = :id and p.deleted = false 
            """)
    Optional<Purchase> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select p from Purchase p
             where p.purchaseNumber = :purchaseNumber
              and p.deleted = false 
              """)
    Optional<Purchase> findByPurchaseNumber(@Param("purchaseNumber") String purchaseNumber);

    @Query(""" 
            select p from Purchase p where p.deleted = false 
            and (:purchaseNumber is null or lower(p.purchaseNumber)
             like lower(concat('%', :purchaseNumber, '%'))) and
              (:supplierId is null or p.supplier.id = :supplierId) and
               (:status is null or p.status = :status) and 
               (:fromDate is null or p.purchaseDate >= :fromDate) and
                (:toDate is null or p.purchaseDate <= :toDate)
                 order by p.purchaseDate desc 
                 """)
    Page<Purchase> search(@Param("purchaseNumber") String purchaseNumber,
                          @Param("supplierId") Long supplierId,
                          @Param("status") PurchaseStatus status,
                          @Param("fromDate") LocalDate fromDate,
                          @Param("toDate") LocalDate toDate,
                          Pageable pageable);
}
