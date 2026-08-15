package com.roochi.petflowinventory.purchase.dto.purchaseitem.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Data
public class GetPurchaseItemByIdRequestDto {

    @NotNull
    private Long id;
}
