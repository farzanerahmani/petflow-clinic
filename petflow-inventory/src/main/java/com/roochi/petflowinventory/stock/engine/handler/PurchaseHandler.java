package com.roochi.petflowinventory.stock.engine.handler;

import com.roochi.petflowinventory.stock.command.IncreaseStockCommand;
import com.roochi.petflowinventory.stock.command.TransactionCommand;
import com.roochi.petflowinventory.stock.engine.StockTransactionHandler;
import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stock.repository.StockRepository;
import com.roochi.petflowinventory.stocktransaction.entity.StockTransaction;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockTransactionType;
import com.roochi.petflowinventory.stocktransaction.repository.StockTransactionRepository;
import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowinventory.warehouse.repository.WarehouseRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.drug.repository.DrugRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Component
@RequiredArgsConstructor
public class PurchaseHandler implements StockTransactionHandler {

    private final StockRepository stockRepository;
    private final StockTransactionRepository stockTransactionRepository;
    private final WarehouseRepository warehouseRepository;
    private final DrugRepository drugRepository;

    @Override
    public StockTransactionType supportedType() {
        return StockTransactionType.PURCHASE;
    }

    @Override
    @Transactional
    public void handle(TransactionCommand command) {

        if (!(command instanceof IncreaseStockCommand purchaseCommand)) {
            throw new IllegalArgumentException(
                    "Invalid command for PURCHASE transaction: "
                            + command.getClass().getSimpleName()
            );
        }

        Warehouse warehouse = warehouseRepository
                .findById(purchaseCommand.getWarehouseId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR)
                );

        Drug drug = drugRepository
                .findById(purchaseCommand.getDrugId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR)
                );

        Stock stock = stockRepository
                .findByWarehouseIdAndDrugIdAndBatchNumberAndExpirationDateAndDeletedFalse(
                        purchaseCommand.getWarehouseId(),
                        purchaseCommand.getDrugId(),
                        purchaseCommand.getBatchNumber(),
                        purchaseCommand.getExpirationDate()
                )
                .orElseGet(() -> createStock(
                        purchaseCommand,
                        warehouse,
                        drug
                ));

        BigDecimal quantityBefore =
                stock.getQuantity();

        BigDecimal reservedQuantityBefore =
                stock.getReservedQuantity();

        stock.increase(
                purchaseCommand.getQuantity(),
                purchaseCommand.getUnitCost()
        );

        if (purchaseCommand.getMinimumQuantity() != null) {
            stock.setMinimumQuantity(
                    purchaseCommand.getMinimumQuantity()
            );
        }

        stock.setActive(true);

        stockRepository.save(stock);

        StockTransaction transaction =
                StockTransaction.builder()
                        .stock(stock)
                        .transactionType(
                                StockTransactionType.PURCHASE
                        )
                        .referenceType(
                                purchaseCommand.getReferenceType()
                        )
                        .referenceId(
                                purchaseCommand.getReferenceId()
                        )
                        .referenceNumber(
                                purchaseCommand.getReferenceNumber()
                        )
                        .quantity(
                                purchaseCommand.getQuantity()
                        )
                        .quantityBefore(
                                quantityBefore
                        )
                        .quantityAfter(
                                stock.getQuantity()
                        )
                        .unitCost(
                                purchaseCommand.getUnitCost()
                        )
                        .description(
                                purchaseCommand.getDescription()
                        )
                        .createdBy(
                                purchaseCommand.getPerformedBy()
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

    private Stock createStock(
            IncreaseStockCommand command,
            Warehouse warehouse,
            Drug drug
    ) {

        return Stock.builder()
                .warehouse(warehouse)
                .drug(drug)
                .batchNumber(command.getBatchNumber())
                .expirationDate(command.getExpirationDate())
                .quantity(BigDecimal.ZERO)
                .reservedQuantity(BigDecimal.ZERO)
                .minimumQuantity(
                        command.getMinimumQuantity() != null
                                ? command.getMinimumQuantity()
                                : BigDecimal.ZERO
                )
                .averageUnitCost(
                        command.getUnitCost() != null
                                ? command.getUnitCost()
                                : BigDecimal.ZERO
                )
                .active(true)
                .build();
    }
}