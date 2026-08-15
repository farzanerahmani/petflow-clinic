package com.roochi.petflowinventory.purchase.service.command;

import com.roochi.petflowinventory.purchase.dto.purchase.request.AddPurchaseRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchase.request.CancelPurchaseRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchase.request.CompletePurchaseRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchase.request.UpdatePurchaseRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.AddPurchaseResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.CancelPurchaseResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.CompletePurchaseResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.UpdatePurchaseResponseDto;
import com.roochi.petflowinventory.purchase.dto.request.*;
import com.roochi.petflowinventory.purchase.dto.response.*;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
public interface PurchaseCommandService {

    AddPurchaseResponseDto addPurchase(AddPurchaseRequestDto requestDto);

    UpdatePurchaseResponseDto updatePurchase(UpdatePurchaseRequestDto requestDto);

    CompletePurchaseResponseDto CompletePurchase(CompletePurchaseRequestDto requestDto);

    CancelPurchaseResponseDto CancelPurchase(CancelPurchaseRequestDto requestDto);
}
