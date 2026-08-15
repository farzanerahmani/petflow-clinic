package com.roochi.petflowinventory.reservation.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 8/6/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveReservationRequestDto {
    @NotNull
    private Long reservationId;

    @NotNull
    private Long drugId;

    @NotNull
    @DecimalMin(value = "0.001")
    private BigDecimal quantity;



}
