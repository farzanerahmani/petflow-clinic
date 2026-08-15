package com.roochi.petflowinventory.reservation.entity;

import com.roochi.petflowshared.entity.BaseEntity;
import com.roochi.petflowvisit.drug.entity.Drug;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/1/2026
 */
@Entity
@Table(name = "reservation_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationItem extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "drug_id")
    private Drug drug;


    @Column(nullable = false, precision = 18, scale = 3)
    private BigDecimal quantity;

    /**
     * Batch انتخاب شده برای رزرو
     */
    @Column(length = 50)
    private String batchNumber;



    /**
     * تاریخ انقضای Batch
     */
    private LocalDate expirationDate;


}