package com.roochi.petflowinventory.purchase.dto.purchaseitem.response;
import lombok.*;

import java.math.BigDecimal;
/**
 * @author farzane.rahmani
 * @created 7/28/2026
 */



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseItemSummaryDto {
    private Long id;
    private String drugCode;
    private String drugName;
    private BigDecimal quantity;
    private BigDecimal lineTotal;
}
