package com.roochi.petflowinventory.reservation.dto.response;

import lombok.*;

/**
 * @author farzane.rahmani
 * @created 8/6/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveReservationResponseDto {


    private Long reservationId;


    private String message;

}
