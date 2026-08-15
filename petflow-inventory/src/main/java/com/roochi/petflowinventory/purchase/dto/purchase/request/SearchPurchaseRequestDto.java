package com.roochi.petflowinventory.purchase.dto.purchase.request;

import com.roochi.petflowinventory.purchase.entity.enums.PurchaseStatus;
import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SearchPurchaseRequestDto extends PageRequestDto {
    private String purchaseNumber;
    private Long supplierId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private PurchaseStatus status;
}
