package com.roochi.petflowinventory.stocktransaction.dto.response;

import com.roochi.petflowinventory.stocktransaction.entity.enums.StockReferenceType;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockTransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockTransactionResponseDto {

    private Long id;

    private Long stockId;

    private Long drugId;

    private String drugName;

    private String batchNumber;

    private Long warehouseId;

    private String warehouseName;


    private StockTransactionType transactionType;

    private StockReferenceType referenceType;

    private Long referenceId;

    private String referenceNumber;


    private BigDecimal quantity;

    private BigDecimal quantityBefore;

    private BigDecimal quantityAfter;


    private BigDecimal reservedQuantityBefore;

    private BigDecimal reservedQuantityAfter;


    private BigDecimal unitCost;


    private String description;

    private String createdBy;

    private LocalDateTime createdAt;

}
