package com.roochi.petflowinventory.stock.command;

import com.roochi.petflowinventory.stocktransaction.entity.enums.StockReferenceType;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockTransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Getter
@Setter
public abstract class TransactionCommand {
    /**
     * نوع تراکنش
     */
    private StockTransactionType transactionType;

    /**
     * مرجع تراکنش
     * Purchase
     * Sale
     * Transfer
     * ...
     */
    private StockReferenceType referenceType;

    /**
     * شناسه مرجع
     */
    private Long referenceId;

    /**
     * شماره سند
     */
    private String referenceNumber;

    /**
     * توضیحات
     */
    private String description;

    /**
     * کاربری که عملیات را انجام داده
     */
    private String performedBy;

    /**
     * مقدار عملیات
     */
    private BigDecimal quantity;

    private String batchNumber;


    private LocalDate expirationDate;

}
