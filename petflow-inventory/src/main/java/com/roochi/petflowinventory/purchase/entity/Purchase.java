package com.roochi.petflowinventory.purchase.entity;

import com.roochi.petflowinventory.purchase.entity.enums.PurchaseStatus;
import com.roochi.petflowinventory.supplier.entity.Supplier;
import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */

@Entity
@Table(
        name = "purchase",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "purchase_number"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase extends SoftDeleteEntity {

    @Column(name = "purchase_number", nullable = false, length = 30)
    private String purchaseNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @Column(nullable = false)
    private LocalDate purchaseDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PurchaseStatus status;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;

    @Column(length = 500)
    private String description;

}
