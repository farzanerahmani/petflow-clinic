package com.roochi.petflowinventory.purchase.service.command;

import com.roochi.petflowinventory.purchase.dto.purchaseitem.request.*;
import com.roochi.petflowinventory.purchase.dto.purchaseitem.response.*;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
public interface PurchaseItemCommandService {

    AddPurchaseItemResponseDto addPurchaseItem(AddPurchaseItemRequestDto requestDto);

    UpdatePurchaseItemResponseDto updatePurchaseItem(UpdatePurchaseItemRequestDto requestDto);

    DeletePurchaseItemResponseDto deletePurchaseItem(DeletePurchaseItemRequestDto requestDto);
}
