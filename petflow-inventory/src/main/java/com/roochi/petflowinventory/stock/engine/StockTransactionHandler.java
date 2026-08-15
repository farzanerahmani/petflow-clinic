package com.roochi.petflowinventory.stock.engine;

import com.roochi.petflowinventory.stock.command.TransactionCommand;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockTransactionType;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
public interface StockTransactionHandler {
    StockTransactionType supportedType();


    void handle(TransactionCommand command);
}
