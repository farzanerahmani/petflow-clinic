package com.roochi.petflowinventory.purchase.dto.purchaseitem.response;

import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/28/2026
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SearchPurchaseItemResponseDto extends PageResponseDto<PurchaseItemSummaryDto> {

}
