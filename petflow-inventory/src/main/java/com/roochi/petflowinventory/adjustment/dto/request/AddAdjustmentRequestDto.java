package com.roochi.petflowinventory.adjustment.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
@Getter
@Setter
public class AddAdjustmentRequestDto {

    @NotNull
    private Long warehouseId;

    @NotNull
    private Long drugId;

    @NotNull
    @Size(max = 50)
    private String batchNumber;

    private LocalDate expirationDate;

    @NotNull
    @DecimalMin(value = "0")
    private BigDecimal newQuantity;

    @Size(max = 500)
    private String reason;
}
