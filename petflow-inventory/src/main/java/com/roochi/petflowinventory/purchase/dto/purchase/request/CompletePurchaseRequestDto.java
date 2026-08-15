package com.roochi.petflowinventory.purchase.dto.purchase.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
@Data
public class CompletePurchaseRequestDto {

    @NotNull
    private Long purchaseId;
}
