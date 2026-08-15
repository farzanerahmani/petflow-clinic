package com.roochi.petflowinventory.stock.engine.handler;

import com.roochi.petflowinventory.stock.command.ReleaseReservationCommand;
import com.roochi.petflowinventory.stock.command.TransactionCommand;
import com.roochi.petflowinventory.stock.engine.StockTransactionHandler;
import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stock.repository.StockRepository;
import com.roochi.petflowinventory.stocktransaction.entity.StockTransaction;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockTransactionType;
import com.roochi.petflowinventory.stocktransaction.repository.StockTransactionRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 8/6/2026
 */
@Component
@RequiredArgsConstructor
public class ReleaseReservationHandler implements StockTransactionHandler {

    private final StockRepository stockRepository;
    private final StockTransactionRepository stockTransactionRepository;

    @Override
    public StockTransactionType supportedType() {
        return StockTransactionType.RELEASE_RESERVATION;
    }

    @Override
    @Transactional
    public void handle(TransactionCommand command) {

        if (!(command instanceof ReleaseReservationCommand releaseCommand)) {
            throw new IllegalArgumentException(
                    "Invalid command for RELEASE_RESERVATION transaction: "
                            + command.getClass().getSimpleName()
            );
        }

        Stock stock = stockRepository
                .findByWarehouseIdAndDrugIdAndBatchNumberAndExpirationDateAndDeletedFalse(
                        releaseCommand.getWarehouseId(),
                        releaseCommand.getDrugId(),
                        releaseCommand.getBatchNumber(),
                        releaseCommand.getExpirationDate()
                )
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR)
                );

        BigDecimal quantityBefore =
                stock.getQuantity();

        BigDecimal reservedQuantityBefore =
                stock.getReservedQuantity();

        stock.releaseReservation(
                releaseCommand.getQuantity()
        );

        stockRepository.save(stock);

        StockTransaction transaction =
                StockTransaction.builder()
                        .stock(stock)
                        .transactionType(
                                StockTransactionType.RELEASE_RESERVATION
                        )
                        .referenceType(
                                releaseCommand.getReferenceType()
                        )
                        .referenceId(
                                releaseCommand.getReferenceId()
                        )
                        .referenceNumber(
                                releaseCommand.getReferenceNumber()
                        )
                        .quantity(
                                releaseCommand.getQuantity()
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
                                releaseCommand.getDescription()
                        )
                        .createdBy(
                                releaseCommand.getPerformedBy()
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