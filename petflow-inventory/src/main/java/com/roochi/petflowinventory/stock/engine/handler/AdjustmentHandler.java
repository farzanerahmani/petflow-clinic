package com.roochi.petflowinventory.stock.engine.handler;

import com.roochi.petflowinventory.stock.command.AdjustmentStockCommand;
import com.roochi.petflowinventory.stock.command.TransactionCommand;
import com.roochi.petflowinventory.stock.engine.StockTransactionHandler;
import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stock.repository.StockRepository;
import com.roochi.petflowinventory.stocktransaction.entity.StockTransaction;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockTransactionType;
import com.roochi.petflowinventory.stocktransaction.repository.StockTransactionRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 7/31/2026
 */
@Component
@RequiredArgsConstructor
public class AdjustmentHandler implements StockTransactionHandler {

    private final StockRepository stockRepository;

    private final StockTransactionRepository stockTransactionRepository;

    @Override
    public StockTransactionType supportedType() {
        return StockTransactionType.ADJUSTMENT;
    }

    @Override
    @Transactional
    public void handle(TransactionCommand command) {

        if (!(command instanceof AdjustmentStockCommand adjustmentCommand)) {
            throw new IllegalArgumentException(
                    "Invalid command for ADJUSTMENT transaction: "
                            + command.getClass().getSimpleName()
            );
        }

        Stock stock =
                stockRepository
                        .findByWarehouseIdAndDrugIdAndBatchNumberAndExpirationDateAndDeletedFalse(
                                adjustmentCommand.getWarehouseId(),
                                adjustmentCommand.getDrugId(),
                                adjustmentCommand.getBatchNumber(),
                                adjustmentCommand.getExpirationDate()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        BigDecimal quantityBefore =
                stock.getQuantity();

        BigDecimal reservedQuantityBefore =
                stock.getReservedQuantity();

        stock.adjust(
                adjustmentCommand.getNewQuantity()
        );

        stockRepository.save(stock);

        BigDecimal difference =
                stock.getQuantity()
                        .subtract(quantityBefore);

        StockTransaction transaction =
                StockTransaction.builder()
                        .stock(stock)
                        .transactionType(
                                StockTransactionType.ADJUSTMENT
                        )
                        .referenceType(
                                adjustmentCommand.getReferenceType()
                        )
                        .referenceId(
                                adjustmentCommand.getReferenceId()
                        )
                        .referenceNumber(
                                adjustmentCommand.getReferenceNumber()
                        )
                        .quantity(
                                difference.abs()
                        )
                        .quantityBefore(
                                quantityBefore
                        )
                        .quantityAfter(
                                stock.getQuantity()
                        )
                        .unitCost(
                                stock.getAverageUnitCost()
                        )
                        .description(
                                adjustmentCommand.getReason()
                        )
                        .createdBy(
                                adjustmentCommand.getPerformedBy()
                        )
                        .reservedQuantityBefore(
                                reservedQuantityBefore
                        )
                        .reservedQuantityAfter(
                                stock.getReservedQuantity()
                        )
                        .build();

        stockTransactionRepository.save(transaction);
    }
}