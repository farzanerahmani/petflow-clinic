package com.roochi.petflowinventory.stock.domain;

import com.roochi.petflowinventory.stock.command.IncreaseStockCommand;
import com.roochi.petflowinventory.stock.command.TransactionCommand;
import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stock.model.StockSnapshot;
import com.roochi.petflowinventory.stocktransaction.entity.StockTransaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 7/30/2026
 */
@Component
public class StockTransactionFactory {

    public StockTransaction create(
            Stock stock,
            TransactionCommand command,
            StockSnapshot snapshot,
            BigDecimal unitCost) {

        StockTransaction transaction = new StockTransaction();

        transaction.setStock(stock);

        transaction.setTransactionType(command.getTransactionType());

        transaction.setReferenceType(command.getReferenceType());
        transaction.setReferenceId(command.getReferenceId());
        transaction.setReferenceNumber(command.getReferenceNumber());

        transaction.setQuantity(command.getQuantity());

        transaction.setQuantityBefore(snapshot.getQuantityBefore());
        transaction.setQuantityAfter(snapshot.getQuantityAfter());

        transaction.setReservedQuantityBefore(snapshot.getReservedQuantityBefore());
        transaction.setReservedQuantityAfter(snapshot.getReservedQuantityAfter());

        transaction.setUnitCost(unitCost);

        transaction.setDescription(command.getDescription());

        transaction.setCreatedBy(command.getPerformedBy());

        return transaction;
    }
}
