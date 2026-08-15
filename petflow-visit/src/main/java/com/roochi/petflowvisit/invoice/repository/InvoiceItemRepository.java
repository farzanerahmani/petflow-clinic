package com.roochi.petflowvisit.invoice.repository;


import com.roochi.petflowvisit.invoice.entity.InvoiceItem;
import com.roochi.petflowvisit.invoice.entity.enums.InvoiceItemType;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */


import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

    @Override
    @Query("""
            select ii
            from InvoiceItem ii
            where ii.id = :id
              and ii.deleted = false
            """)
    Optional<InvoiceItem> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select ii
            from InvoiceItem ii
            where ii.id = :id
              and ii.deleted = false
            """)
    Optional<InvoiceItem> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select ii
            from InvoiceItem ii
            where ii.invoice.id = :invoiceId
              and ii.deleted = false
            order by ii.id
            """)
    List<InvoiceItem> findByInvoiceId(@Param("invoiceId") Long invoiceId);

    @Query("""
            select ii
            from InvoiceItem ii
            where ii.deleted = false
              and (:invoiceId is null or ii.invoice.id = :invoiceId)
              and (:itemType is null or ii.itemType = :itemType)
              and (:referenceId is null or ii.referenceId = :referenceId)
            order by ii.id
            """)
    Page<InvoiceItem> search(
            @Param("invoiceId") Long invoiceId,
            @Param("itemType") InvoiceItemType itemType,
            @Param("referenceId") Long referenceId,
            Pageable pageable);

}
