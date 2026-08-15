package com.roochi.petflowinventory.purchase.dto.purchaseitem.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/28/2026
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddPurchaseItemRequestDto {
    @NotNull
    private Long purchaseId;
    @NotNull
    private Long drugId;
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal quantity;
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal unitPrice;
    @Size(max = 50)
    private String batchNumber;
    private LocalDate expirationDate;
}
