package com.roochi.petflowinventory.stocktransaction.entity.enums;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
public enum StockTransactionType {
    PURCHASE,
    SALE,
    ADJUSTMENT_INCREASE,
    ADJUSTMENT_DECREASE,
    TRANSFER_IN,
    TRANSFER_OUT,
    RESERVATION,
    RELEASE_RESERVATION,
    RETURN_FROM_CUSTOMER,
    RETURN_TO_SUPPLIER,
    EXPIRED,
    DAMAGED,
    MANUAL,
    ADJUSTMENT
}
