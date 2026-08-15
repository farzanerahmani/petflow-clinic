package com.roochi.petflowinventory.adjustment.entity;

import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/1/2026
 */
@Entity
@Table(name = "adjustment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adjustment extends SoftDeleteEntity {

    @Column(nullable = false, unique = true, length = 30)
    private String adjustmentNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(nullable = false, precision = 18, scale = 3)
    private BigDecimal previousQuantity;

    @Column(nullable = false, precision = 18, scale = 3)
    private BigDecimal adjustedQuantity;

    @Column(nullable =false, precision =18, scale =3)
    private BigDecimal difference;

    @Column(nullable = false)
    private LocalDate adjustmentDate;

    @Column(length = 500)
    private String reason;

}
