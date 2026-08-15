package com.roochi.petflowinventory.reservation.dto.response;

import lombok.*;

/**
 * @author farzane.rahmani
 * @created 8/5/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReleaseReservationResponseDto {

    private Long reservationId;

    private String message;

}
