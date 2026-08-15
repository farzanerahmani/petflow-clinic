package com.roochi.petflowinventory.purchase.domain;

import com.roochi.petflowinventory.purchase.entity.Purchase;
import com.roochi.petflowinventory.purchase.entity.PurchaseItem;
import com.roochi.petflowinventory.service.InventoryTransactionService;
import com.roochi.petflowinventory.stock.command.IncreaseStockCommand;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockReferenceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/31/2026
 */
@Component
@RequiredArgsConstructor
public class PurchaseInventorySynchronizer {

    private final InventoryTransactionService inventoryTransactionService;

    public void synchronize(
            Purchase purchase,
            List<PurchaseItem> purchaseItems,
            String performedBy) {

        for (PurchaseItem item : purchaseItems) {

            IncreaseStockCommand command = new IncreaseStockCommand();

            command.setWarehouseId(
                    purchase.getWarehouse().getId());

            command.setDrugId(
                    item.getDrug().getId());

            command.setBatchNumber(
                    item.getBatchNumber());

            command.setExpirationDate(
                    item.getExpirationDate());

            command.setQuantity(
                    item.getQuantity());

            command.setUnitCost(
                    item.getUnitPrice());
// TODO: Resolve minimum quantity from inventory policy
            command.setMinimumQuantity(BigDecimal.ZERO);

            command.setReferenceType(
                    StockReferenceType.PURCHASE);

            command.setReferenceId(
                    purchase.getId());

            command.setReferenceNumber(
                    purchase.getPurchaseNumber());

            command.setDescription(
                    purchase.getDescription());

            command.setPerformedBy(
                    performedBy);

            inventoryTransactionService.purchase(command);
        }
    }
}
