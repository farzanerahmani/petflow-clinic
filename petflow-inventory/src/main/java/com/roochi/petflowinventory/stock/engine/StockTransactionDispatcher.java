package com.roochi.petflowinventory.stock.engine;

import com.roochi.petflowinventory.stock.command.TransactionCommand;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
public interface StockTransactionDispatcher {
    void dispatch(TransactionCommand command);
}
