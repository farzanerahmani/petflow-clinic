package com.roochi.petflowinventory.stocktransaction.entity;

import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockReferenceType;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockTransactionType;
import com.roochi.petflowshared.entity.AuditingEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Entity
@Table(name = "stock_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockTransaction extends AuditingEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id")
    private Stock stock;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private StockTransactionType transactionType;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private StockReferenceType referenceType;
    @Column(nullable = false)
    private Long referenceId;
    @Column(length = 100)
    private String referenceNumber;
    @Column(nullable = false, precision = 18, scale = 3)
    private BigDecimal quantity;
    @Column(nullable = false, precision = 18, scale = 3)
    private BigDecimal quantityBefore;
    @Column(nullable = false, precision = 18, scale = 3)
    private BigDecimal quantityAfter;
    @Column(nullable = false, precision = 18, scale = 4)
    private BigDecimal unitCost;
    @Column(length = 500)
    private String description;
    @Column(length = 100)
    private String createdBy;
    @Column(precision = 18, scale = 3)
    private BigDecimal reservedQuantityBefore;

    @Column(precision = 18, scale = 3)
    private BigDecimal reservedQuantityAfter;
}
