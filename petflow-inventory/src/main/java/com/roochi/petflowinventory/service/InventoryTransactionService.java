package com.roochi.petflowinventory.service;

import com.roochi.petflowinventory.stock.command.*;
import com.roochi.petflowinventory.stock.domain.StockAllocator;
import com.roochi.petflowinventory.stock.domain.StockCommandFactory;
import com.roochi.petflowinventory.stock.engine.StockTransactionDispatcher;
import com.roochi.petflowinventory.stock.model.StockAllocation;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockTransactionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/31/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class InventoryTransactionService {

    private final StockTransactionDispatcher dispatcher;

    private final StockAllocator stockAllocator;

    private final StockCommandFactory stockCommandFactory;

    public void purchase(IncreaseStockCommand command) {

        command.setTransactionType(StockTransactionType.PURCHASE);

        dispatcher.dispatch(command);
    }

    public void sale(SaleCommand command) {

        List<StockAllocation> allocations =
                stockAllocator.allocate(
                        command.getWarehouseId(),
                        command.getDrugId(),
                        command.getQuantity()
                );

        for (StockAllocation allocation : allocations) {

            dispatcher.dispatch(
                    stockCommandFactory.createSaleCommand(
                            command,
                            allocation
                    )
            );
        }
    }

    public void reserve(ReservationCommand command) {

        List<StockAllocation> allocations =
                stockAllocator.allocate(
                        command.getWarehouseId(),
                        command.getDrugId(),
                        command.getQuantity()
                );

        for (StockAllocation allocation : allocations) {

            dispatcher.dispatch(
                    stockCommandFactory.createReserveCommand(
                            command,
                            allocation
                    )
            );
        }
    }

    public void releaseReservation(
            ReleaseReservationCommand command) {

        command.setTransactionType(
                StockTransactionType.RELEASE_RESERVATION
        );

        dispatcher.dispatch(command);
    }

    public void adjust(
            AdjustmentStockCommand command) {

        command.setTransactionType(
                StockTransactionType.ADJUSTMENT
        );

        dispatcher.dispatch(command);
    }

}