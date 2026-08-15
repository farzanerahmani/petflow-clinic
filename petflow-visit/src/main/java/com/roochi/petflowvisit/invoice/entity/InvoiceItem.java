package com.roochi.petflowvisit.invoice.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.invoice.entity.enums.InvoiceItemType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */


@Entity
@Table(name = "invoice_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceItem extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private InvoiceItemType itemType;

    @Column(nullable = false, length = 200)
    private String itemName;

    @Column
    private Long referenceId;

    @Column(nullable = false)
    @Builder.Default
    private Integer quantity = 1;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 12, scale = 2)
    @Builder.Default
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPrice;

    @Column(length = 1000)
    private String note;
}
