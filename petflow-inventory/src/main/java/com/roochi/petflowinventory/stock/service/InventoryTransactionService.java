package com.roochi.petflowinventory.stock.service;

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


    /**
     * ثبت ورود کالا به موجودی
     * معمولاً از Purchase فراخوانی می‌شود.
     */
    public void purchase(
            IncreaseStockCommand command
    ) {

        command.setTransactionType(
                StockTransactionType.PURCHASE
        );

        dispatcher.dispatch(command);
    }


    /**
     * فروش مستقیم از موجودی.
     *
     * ابتدا Batchهای مناسب توسط Allocator انتخاب می‌شوند،
     * سپس برای هر Batch یک SaleStockCommand ساخته می‌شود.
     */
    public void sale(
            SaleCommand command
    ) {

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


    /**
     * رزرو موجودی.
     *
     * Allocator مشخص می‌کند رزرو از کدام Batchها انجام شود.
     */
    public void reserve(
            ReservationCommand command
    ) {

        List<StockAllocation> allocations =
                stockAllocator.allocate(
                        command.getWarehouseId(),
                        command.getDrugId(),
                        command.getQuantity()
                );

        for (StockAllocation allocation: allocations) {
            dispatcher.dispatch(
                    stockCommandFactory.createReserveCommand(
                            command,
                            allocation
                    )
            );
        }
    }


    /**
     * آزاد کردن Reservation.
     *
     * این عملیات دیگر Allocator نمی‌خواهد،
     * چون باید دقیقاً همان Stock/Batch رزروشده را آزاد کنیم.
     */
    public void releaseReservation(
            ReleaseReservationCommand command
    ) {

        command.setTransactionType(
                StockTransactionType.RELEASE_RESERVATION
        );

        dispatcher.dispatch(command);
    }


    /**
     * اصلاح دستی موجودی.
     */
    public void adjust(
            AdjustmentStockCommand command
    ) {

        command.setTransactionType(
                StockTransactionType.ADJUSTMENT
        );

        dispatcher.dispatch(command);
    }
}