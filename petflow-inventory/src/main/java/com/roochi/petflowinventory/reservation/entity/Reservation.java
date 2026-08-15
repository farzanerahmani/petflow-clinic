package com.roochi.petflowinventory.reservation.entity;

import com.roochi.petflowinventory.reservation.entity.enums.ReservationStatus;
import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/1/2026
 */
@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation extends SoftDeleteEntity {


    @Column(nullable = false, unique = true, length = 50)
    private String reservationNumber;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;


    /**
     * برای اتصال به Visit یا Prescription
     * فعلاً Generic نگه می‌داریم
     */
    @Column
    private Long referenceId;


    @Column(length = 50)
    private String referenceType;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private ReservationStatus status;


    @Column(nullable = false)
    private LocalDate reservationDate;


    @Column(length = 500)
    private String description;

}
