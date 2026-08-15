package com.roochi.petflowinventory.sale.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddSaleItemRequestDto {

    @NotNull
    private Long saleId;

    @NotNull
    private Long drugId;

    @NotNull
    @DecimalMin("0.001")
    private BigDecimal quantity;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal unitPrice;

}
