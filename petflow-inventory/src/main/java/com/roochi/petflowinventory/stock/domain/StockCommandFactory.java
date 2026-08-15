package com.roochi.petflowinventory.stock.domain;

import com.roochi.petflowinventory.stock.command.ReservationCommand;
import com.roochi.petflowinventory.stock.command.ReserveStockCommand;
import com.roochi.petflowinventory.stock.command.SaleCommand;
import com.roochi.petflowinventory.stock.command.SaleStockCommand;
import com.roochi.petflowinventory.stock.model.StockAllocation;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/31/2026
 */

@Component
public class StockCommandFactory {

    public SaleStockCommand createSaleCommand(
            SaleCommand command,
            StockAllocation allocation
    ) {

        SaleStockCommand stockCommand =
                new SaleStockCommand();

        stockCommand.setWarehouseId(
                allocation.getWarehouseId()
        );

        stockCommand.setDrugId(
                allocation.getDrugId()
        );

        stockCommand.setBatchNumber(
                allocation.getBatchNumber()
        );

        stockCommand.setExpirationDate(
                allocation.getExpirationDate()
        );

        stockCommand.setQuantity(
                allocation.getQuantity()
        );

        stockCommand.setUnitCost(
                allocation.getUnitCost()
        );

        stockCommand.setReferenceType(
                command.getReferenceType()
        );

        stockCommand.setReferenceId(
                command.getReferenceId()
        );

        stockCommand.setReferenceNumber(
                command.getReferenceNumber()
        );

        stockCommand.setDescription(
                command.getDescription()
        );

        stockCommand.setPerformedBy(
                command.getPerformedBy()
        );

        return stockCommand;
    }

    public ReserveStockCommand createReserveCommand(
            ReservationCommand command,
            StockAllocation allocation
    ) {

        ReserveStockCommand stockCommand =
                new ReserveStockCommand();

        stockCommand.setWarehouseId(
                allocation.getWarehouseId()
        );

        stockCommand.setDrugId(
                allocation.getDrugId()
        );

        stockCommand.setBatchNumber(
                allocation.getBatchNumber()
        );

        stockCommand.setExpirationDate(
                allocation.getExpirationDate()
        );

        stockCommand.setQuantity(
                allocation.getQuantity()
        );

        stockCommand.setUnitCost(
                allocation.getUnitCost()
        );

        stockCommand.setReferenceType(
                command.getReferenceType()
        );

        stockCommand.setReferenceId(
                command.getReferenceId()
        );

        stockCommand.setReferenceNumber(
                command.getReferenceNumber()
        );

        stockCommand.setDescription(
                command.getDescription()
        );

        stockCommand.setPerformedBy(
                command.getPerformedBy()
        );

        return stockCommand;
    }
}