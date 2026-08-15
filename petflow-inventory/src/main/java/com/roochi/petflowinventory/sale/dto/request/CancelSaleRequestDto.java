package com.roochi.petflowinventory.sale.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelSaleRequestDto {

    @NotNull
    private Long saleId;

}
