package com.roochi.petflowinventory.reservation.dto.response;

import com.roochi.petflowinventory.reservation.entity.enums.ReservationStatus;
import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/5/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponseDto {

    private Long id;

    private String reservationNumber;

    private Long warehouseId;

    private String referenceType;

    private Long referenceId;

    private ReservationStatus status;

    private LocalDate reservationDate;

    private String description;

}
