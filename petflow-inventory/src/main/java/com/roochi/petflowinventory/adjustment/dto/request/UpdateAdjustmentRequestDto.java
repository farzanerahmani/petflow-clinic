package com.roochi.petflowinventory.adjustment.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Getter
@Setter
public class UpdateAdjustmentRequestDto {

    @NotNull
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    private BigDecimal newQuantity;

    @Size(max = 500)
    private String reason;
}
