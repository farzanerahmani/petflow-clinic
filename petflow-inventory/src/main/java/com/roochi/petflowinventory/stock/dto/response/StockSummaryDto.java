package com.roochi.petflowinventory.stock.dto.response;

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
public class StockSummaryDto {
    private Long id;
    private String warehouseName;
    private String drugCode;
    private String drugName;
    private String batchNumber;
    private LocalDate expirationDate;
    private BigDecimal quantity;
}
