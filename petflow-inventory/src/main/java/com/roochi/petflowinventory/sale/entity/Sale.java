package com.roochi.petflowinventory.sale.entity;


import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowinventory.sale.entity.enums.SaleStatus;
import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */


@Entity
@Table(name = "sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale extends SoftDeleteEntity {

    @Column(nullable = false, unique = true, length = 30)
    private String saleNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    /**
     * اگر مشتری وجود داشت.
     * برای فروش آزاد nullable می‌ماند.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    @Column(nullable = false)
    private LocalDate saleDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SaleStatus status;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;

    @Column(length = 500)
    private String description;
}
