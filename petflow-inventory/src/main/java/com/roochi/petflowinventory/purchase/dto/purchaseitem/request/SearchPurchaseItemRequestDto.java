package com.roochi.petflowinventory.purchase.dto.purchaseitem.request;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/28/2026
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SearchPurchaseItemRequestDto extends PageRequestDto {
    private Long purchaseId;
    private Long drugId;
}
