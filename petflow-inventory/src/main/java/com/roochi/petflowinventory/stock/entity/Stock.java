package com.roochi.petflowinventory.stock.entity;

import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.drug.entity.Drug;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Entity
@Table(name = "stock", uniqueConstraints = {@UniqueConstraint
        (columnNames = {"clinic_id", "warehouse_id", "drug_id", "batch_number"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock extends SoftDeleteEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "drug_id")
    private Drug drug;
    @Column(nullable = false, length = 50)
    private String batchNumber;
    @Column
    private LocalDate expirationDate;
    @Column(nullable = false, precision = 18, scale = 3)
    private BigDecimal quantity;
    @Column(nullable = false, precision = 18, scale = 3)
    private BigDecimal reservedQuantity;
    @Column(nullable = false, precision = 18, scale = 3)
    private BigDecimal minimumQuantity;
    @Column(nullable = false, precision = 18, scale = 4)
    private BigDecimal averageUnitCost;
    @Column(nullable = false)
    private Boolean active;

    public void increase(BigDecimal quantity,
                         BigDecimal averageUnitCost) {

        if (quantity == null ||
                quantity.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        this.quantity = this.quantity.add(quantity);

        this.averageUnitCost = averageUnitCost;
    }
    public void reserve(BigDecimal quantity) {

        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        if (getAvailableQuantity().compareTo(quantity) < 0) {
            throw new IllegalStateException("Insufficient available stock.");
        }

        this.reservedQuantity = this.reservedQuantity.add(quantity);
    }

    public void releaseReservation(BigDecimal quantity) {

        if (quantity == null ||
                quantity.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        if (reservedQuantity.compareTo(quantity) < 0) {

            throw new IllegalStateException("Reserved quantity is insufficient.");
        }

        this.reservedQuantity =
                this.reservedQuantity.subtract(quantity);
    }

    public void decrease(BigDecimal quantity) {

        if (quantity == null ||
                quantity.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        if (getAvailableQuantity().compareTo(quantity) < 0) {

            throw new IllegalStateException("Insufficient stock.");
        }

        this.quantity =
                this.quantity.subtract(quantity);
    }

    public void adjust(BigDecimal newQuantity) {

        if (newQuantity == null ||
                newQuantity.compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Quantity cannot be negative."
            );
        }

        if (newQuantity.compareTo(reservedQuantity) < 0) {

            throw new IllegalStateException(
                    "Adjusted quantity cannot be less than reserved quantity."
            );
        }

        this.quantity = newQuantity;
    }

    public void consumeReserved(BigDecimal quantity) {

        if (quantity == null ||
                quantity.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Quantity must be greater than zero."
            );
        }

        if (reservedQuantity.compareTo(quantity) < 0) {

            throw new IllegalStateException(
                    "Reserved quantity is insufficient."
            );
        }

        if (this.quantity.compareTo(quantity) < 0) {

            throw new IllegalStateException(
                    "Stock quantity is insufficient."
            );
        }

        this.quantity =
                this.quantity.subtract(quantity);

        this.reservedQuantity =
                this.reservedQuantity.subtract(quantity);
    }
    @Transient
    public BigDecimal getAvailableQuantity() {

        return quantity.subtract(reservedQuantity);
    }
}