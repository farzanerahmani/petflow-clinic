package com.roochi.petflowvisit.payment.repository;

import com.roochi.petflowvisit.payment.entity.Payment;
import com.roochi.petflowvisit.payment.entity.enums.PaymentMethod;
import com.roochi.petflowvisit.payment.entity.enums.PaymentStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
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
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Override
    @Query("""
            select p
            from Payment p
            where p.id = :id
              and p.deleted = false
            """)
    Optional<Payment> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select p
            from Payment p
            where p.id = :id
              and p.deleted = false
            """)
    Optional<Payment> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select p
            from Payment p
            where p.invoice.id = :invoiceId
              and p.deleted = false
            order by p.paymentDate desc
            """)
    List<Payment> findByInvoiceId(@Param("invoiceId") Long invoiceId);

    @Query("""
            select p
            from Payment p
            where p.deleted = false
              and (:invoiceId is null or p.invoice.id = :invoiceId)
              and (:paymentMethod is null or p.paymentMethod = :paymentMethod)
              and (:status is null or p.status = :status)
              and (:fromDate is null or p.paymentDate >= :fromDate)
              and (:toDate is null or p.paymentDate <= :toDate)
            order by p.paymentDate desc
            """)
    Page<Payment> search(
            @Param("invoiceId") Long invoiceId,
            @Param("paymentMethod") PaymentMethod paymentMethod,
            @Param("status") PaymentStatus status,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate
            , Pageable pageable);

}
