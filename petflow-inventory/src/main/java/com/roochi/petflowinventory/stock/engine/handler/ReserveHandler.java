package com.roochi.petflowinventory.stock.engine.handler;

import com.roochi.petflowinventory.stock.command.ReservationCommand;
import com.roochi.petflowinventory.stock.command.ReserveStockCommand;
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
public class ReserveHandler implements StockTransactionHandler {

    private final StockRepository stockRepository;
    private final StockTransactionRepository stockTransactionRepository;

    @Override
    public StockTransactionType supportedType() {
        return StockTransactionType.RESERVATION;
    }

    @Override
    @Transactional
    public void handle(TransactionCommand command) {

        if (!(command instanceof ReserveStockCommand reserveCommand)) {
            throw new IllegalArgumentException(
                    "Invalid command for RESERVATION transaction: "
                            + command.getClass().getSimpleName()
            );
        }

        Stock stock = stockRepository
                .findByWarehouseIdAndDrugIdAndBatchNumberAndExpirationDateAndDeletedFalse(
                        reserveCommand.getWarehouseId(),
                        reserveCommand.getDrugId(),
                        reserveCommand.getBatchNumber(),
                        reserveCommand.getExpirationDate()
                )
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR)
                );

        BigDecimal quantityBefore =
                stock.getQuantity();

        BigDecimal reservedQuantityBefore =
                stock.getReservedQuantity();

        stock.reserve(
                reserveCommand.getQuantity()
        );

        stockRepository.save(stock);

        StockTransaction transaction =
                StockTransaction.builder()
                        .stock(stock)
                        .transactionType(
                                StockTransactionType.RESERVATION
                        )
                        .referenceType(
                                reserveCommand.getReferenceType()
                        )
                        .referenceId(
                                reserveCommand.getReferenceId()
                        )
                        .referenceNumber(
                                reserveCommand.getReferenceNumber()
                        )
                        .quantity(
                                reserveCommand.getQuantity()
                        )
                        .quantityBefore(
                                quantityBefore
                        )
                        .quantityAfter(
                                stock.getQuantity()
                        )
                        .unitCost(
                                reserveCommand.getUnitCost()
                        )
                        .description(
                                reserveCommand.getDescription()
                        )
                        .createdBy(
                                reserveCommand.getPerformedBy()
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