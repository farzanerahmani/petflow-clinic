package com.roochi.petflowinventory.reservation.dto.response;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 8/5/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationItemResponseDto {


    private Long id;

    private Long reservationId;

    private Long drugId;

    private String drugName;

    private BigDecimal quantity;

}
