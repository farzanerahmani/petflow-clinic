package com.roochi.petflowinventory.sale.dto.response;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 8/3/2026
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleItemResponseDto {

    private Long id;

    private Long saleId;

    private Long drugId;

    private String drugCode;

    private String drugName;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private BigDecimal lineTotal;

}
