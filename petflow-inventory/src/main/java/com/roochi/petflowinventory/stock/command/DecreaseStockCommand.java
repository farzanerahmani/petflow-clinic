package com.roochi.petflowinventory.stock.command;

import lombok.Getter;
import lombok.Setter;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */


@Getter
@Setter
public class DecreaseStockCommand extends TransactionCommand {
    private Long stockId;
}
