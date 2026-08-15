package com.roochi.petflowinventory.stocktransaction.dto.request;

import com.roochi.petflowinventory.stocktransaction.entity.enums.StockReferenceType;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockTransactionType;
import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockTransactionSearchRequestDto {

    private Long stockId;

    private Long drugId;

    private Long warehouseId;

    private StockTransactionType transactionType;

    private StockReferenceType referenceType;

    private LocalDate fromDate;

    private LocalDate toDate;

}
