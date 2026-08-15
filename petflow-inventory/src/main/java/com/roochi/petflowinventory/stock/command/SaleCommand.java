package com.roochi.petflowinventory.stock.command;

import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowvisit.drug.entity.Drug;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/31/2026
 */
@Getter
@Setter
public class SaleCommand extends TransactionCommand {

    private Long warehouseId;

    private Long drugId;


}
