package com.roochi.petflowinventory.stock.engine.handler;

import com.roochi.petflowinventory.stock.command.SaleCommand;
import com.roochi.petflowinventory.stock.command.SaleStockCommand;
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
public class SaleHandler implements StockTransactionHandler {

    private final StockRepository stockRepository;

    private final StockTransactionRepository stockTransactionRepository;


    @Override
    public StockTransactionType supportedType() {

        return StockTransactionType.SALE;
    }


    @Override
    @Transactional
    public void handle(
            TransactionCommand command
    ) {

        if (!(command instanceof SaleStockCommand saleCommand)) {

            throw new IllegalArgumentException(
                    "Invalid command for SALE transaction: "
                            + command.getClass().getSimpleName()
            );
        }


        Stock stock =
                stockRepository
                        .findByWarehouseIdAndDrugIdAndBatchNumberAndExpirationDateAndDeletedFalse(
                                saleCommand.getWarehouseId(),
                                saleCommand.getDrugId(),
                                saleCommand.getBatchNumber(),
                                saleCommand.getExpirationDate()
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


        stock.decrease(
                saleCommand.getQuantity()
        );


        stockRepository.save(stock);


        StockTransaction transaction =
                StockTransaction.builder()
                        .stock(stock)

                        .transactionType(
                                StockTransactionType.SALE
                        )

                        .referenceType(
                                saleCommand.getReferenceType()
                        )

                        .referenceId(
                                saleCommand.getReferenceId()
                        )

                        .referenceNumber(
                                saleCommand.getReferenceNumber()
                        )

                        .quantity(
                                saleCommand.getQuantity()
                        )

                        .quantityBefore(
                                quantityBefore
                        )

                        .quantityAfter(
                                stock.getQuantity()
                        )

                        .unitCost(
                                saleCommand.getUnitCost() != null
                                        ? saleCommand.getUnitCost()
                                        : stock.getAverageUnitCost()
                        )

                        .description(
                                saleCommand.getDescription()
                        )

                        .createdBy(
                                saleCommand.getPerformedBy()
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