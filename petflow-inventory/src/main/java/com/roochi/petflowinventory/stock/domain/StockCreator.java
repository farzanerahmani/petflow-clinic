package com.roochi.petflowinventory.stock.domain;

import com.roochi.petflowinventory.stock.command.IncreaseStockCommand;
import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stock.repository.StockRepository;
import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowinventory.warehouse.repository.WarehouseRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.drug.repository.DrugRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Component
@RequiredArgsConstructor
public class StockCreator {

    private final WarehouseRepository warehouseRepository;

    private final DrugRepository drugRepository;

    public Stock create(IncreaseStockCommand command) {

        Warehouse warehouse =
                warehouseRepository.findById(command.getWarehouseId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR));///* WarehouseError.WAREHOUSE_NOT_FOUND */));

        Drug drug =
                drugRepository.findById(command.getDrugId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR));///* DrugError.DRUG_NOT_FOUND */));

        Stock stock = new Stock();

        stock.setWarehouse(warehouse);
        stock.setDrug(drug);

        stock.setBatchNumber(command.getBatchNumber());
        stock.setExpirationDate(command.getExpirationDate());

        stock.setQuantity(command.getQuantity());
        stock.setReservedQuantity(BigDecimal.ZERO);

        stock.setMinimumQuantity(command.getMinimumQuantity());

        stock.setAverageUnitCost(command.getUnitCost());

        stock.setActive(Boolean.TRUE);

        return stock;
    }

}