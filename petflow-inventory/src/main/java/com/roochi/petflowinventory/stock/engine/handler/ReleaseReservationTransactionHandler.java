package com.roochi.petflowinventory.stock.engine.handler;

import com.roochi.petflowinventory.stock.command.ReleaseReservationCommand;
import com.roochi.petflowinventory.stock.command.TransactionCommand;
import com.roochi.petflowinventory.stock.domain.StockFinder;
import com.roochi.petflowinventory.stock.domain.StockTransactionFactory;
import com.roochi.petflowinventory.stock.engine.StockTransactionHandler;
import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stock.model.StockSnapshot;
import com.roochi.petflowinventory.stock.repository.StockRepository;
import com.roochi.petflowinventory.stocktransaction.entity.StockTransaction;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockTransactionType;
import com.roochi.petflowinventory.stocktransaction.repository.StockTransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/31/2026
 */
@Component
@RequiredArgsConstructor
@Transactional
public class ReleaseReservationTransactionHandler implements StockTransactionHandler {

    private final StockFinder stockFinder;

    private final StockRepository stockRepository;

    private final StockTransactionRepository stockTransactionRepository;

    private final StockTransactionFactory stockTransactionFactory;

    @Override
    public StockTransactionType supportedType() {
        return StockTransactionType.RELEASE_RESERVATION;
    }

    @Override
    public void handle(TransactionCommand command) {

        ReleaseReservationCommand release =
                (ReleaseReservationCommand) command;

        Stock stock =
                stockFinder.findById(
                        release.getStockId()
                );

        StockSnapshot snapshot =
                StockSnapshot.before(stock);

        stock.releaseReservation(
                release.getQuantity()
        );

        snapshot = snapshot.after(stock);

        stockRepository.save(stock);

        StockTransaction transaction =
                stockTransactionFactory.create(
                        stock,
                        release,
                        snapshot,
                        stock.getAverageUnitCost()
                );

        stockTransactionRepository.save(transaction);
    }
}
