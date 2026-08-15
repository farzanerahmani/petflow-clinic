package com.roochi.petflowinventory.reservation.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
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
public class ReleaseReservationRequestDto {

    @NotNull
    private Long reservationId;

    @NotNull
    private Long drugId;

    @NotNull
    private String batchNumber;

    private LocalDate expirationDate;

    @NotNull
    @DecimalMin(value = "0.001")
    private BigDecimal quantity;

}
