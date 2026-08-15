package com.roochi.petflowinventory.stocktransaction.service.query;

import com.roochi.petflowinventory.stocktransaction.dto.response.StockTransactionResponseDto;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockReferenceType;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
public interface StockTransactionQueryService {


    List<StockTransactionResponseDto> findByStock(
            Long stockId
    );


    List<StockTransactionResponseDto> findByReference(
            StockReferenceType referenceType,
            Long referenceId
    );


    List<StockTransactionResponseDto> findByDrug(
            Long drugId
    );


    List<StockTransactionResponseDto> findByWarehouse(
            Long warehouseId
    );
}
