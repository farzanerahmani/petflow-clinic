package com.roochi.petflowinventory.purchase.service.query;

import com.roochi.petflowinventory.purchase.dto.purchaseitem.request.*;
import com.roochi.petflowinventory.purchase.dto.purchaseitem.response.*;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
public interface PurchaseItemQueryService {

    PurchaseItemResponseDto getPurchaseItemById(GetPurchaseItemByIdRequestDto requestDto);

    SearchPurchaseItemResponseDto searchPurchaseItem(SearchPurchaseItemRequestDto requestDto);
}
