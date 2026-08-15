package com.roochi.petflowvisit.payment.entity;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.invoice.entity.Invoice;
import com.roochi.petflowvisit.payment.entity.enums.PaymentMethod;
import com.roochi.petflowvisit.payment.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */


@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentStatus status;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(length = 100)
    private String transactionReference;

    @Column(length = 100)
    private String payerName;

    @Column(length = 1000)
    private String note;

}
