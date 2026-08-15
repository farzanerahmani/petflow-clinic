package com.roochi.petflowinventory.sale.entity;


import com.roochi.petflowshared.entity.BaseEntity;
import com.roochi.petflowvisit.drug.entity.Drug;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */


@Entity
@Table(name = "sale_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @Column(nullable = false, precision = 18, scale = 3)
    private BigDecimal quantity;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal lineTotal;
}
