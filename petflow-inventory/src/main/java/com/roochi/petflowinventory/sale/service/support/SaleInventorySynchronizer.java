package com.roochi.petflowinventory.sale.service.support;

import com.roochi.petflowinventory.service.InventoryTransactionService;
import com.roochi.petflowinventory.stock.command.SaleCommand;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockReferenceType;
import com.roochi.petflowinventory.sale.entity.Sale;
import com.roochi.petflowinventory.sale.entity.SaleItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */


@Component
@RequiredArgsConstructor
public class SaleInventorySynchronizer {

    private final InventoryTransactionService inventoryTransactionService;

    public void synchronize(
            Sale sale,
            List<SaleItem> items,
            String performedBy) {

        for (SaleItem item : items) {

            SaleCommand command = new SaleCommand();

            command.setWarehouseId(
                    sale.getWarehouse().getId());

            command.setDrugId(
                    item.getDrug().getId());

            command.setQuantity(
                    item.getQuantity());

            command.setReferenceType(
                    StockReferenceType.SALE);

            command.setReferenceId(
                    sale.getId());

            command.setReferenceNumber(
                    sale.getSaleNumber());

            command.setDescription(
                    sale.getDescription());

            command.setPerformedBy(
                    performedBy);

            inventoryTransactionService.sale(command);
        }
    }
}
