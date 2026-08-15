package com.roochi.petflowinventory.purchase.service.query;

import com.roochi.petflowinventory.purchase.dto.purchase.request.GetPurchaseByIdRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchase.request.SearchPurchaseRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.PurchaseResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.SearchPurchaseResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
public interface PurchaseQueryService {

    PurchaseResponseDto getPurchaseById(GetPurchaseByIdRequestDto requestDto);

    SearchPurchaseResponseDto searchPurchase(SearchPurchaseRequestDto requestDto);
}
