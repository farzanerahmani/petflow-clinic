package com.roochi.petflowvisit.invoice.repository;

import com.roochi.petflowvisit.invoice.entity.Invoice;
import com.roochi.petflowvisit.invoice.entity.enums.InvoiceStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Override
    @Query("""
            select i
            from Invoice i
            where i.id = :id
              and i.deleted = false
            """)
    Optional<Invoice> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select i
            from Invoice i
            where i.id = :id
              and i.deleted = false
            """)
    Optional<Invoice> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select i
            from Invoice i
            where i.deleted = false
              and (:visitId is null or i.visit.id = :visitId)
              and (:status is null or i.status = :status)
              and (:fromDate is null or i.invoiceDate >= :fromDate)
              and (:toDate is null or i.invoiceDate <= :toDate)
            order by i.invoiceDate desc
            """)
    Page<Invoice> search(
            @Param("visitId") Long visitId,
            @Param("status") InvoiceStatus status,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            Pageable pageable);

}