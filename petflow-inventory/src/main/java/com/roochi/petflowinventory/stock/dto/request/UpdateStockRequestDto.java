package com.roochi.petflowinventory.stock.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStockRequestDto {
    @NotNull
    private Long id;
    @NotNull
    private LocalDate expirationDate;
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal minimumQuantity;
}
