package com.roochi.petflowinventory.purchase.service.impl;

import com.roochi.petflowinventory.purchase.entity.Purchase;
import com.roochi.petflowinventory.purchase.repository.PurchaseItemRepository;
import com.roochi.petflowinventory.purchase.repository.PurchaseRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Component
@RequiredArgsConstructor
public class PurchaseTotalCalculator {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;

    @Transactional
    public void recalculate(Long purchaseId) {
        Purchase purchase = purchaseRepository.findByIdForUpdate(purchaseId).orElseThrow(()
                -> new NotFoundException(ErrorCode.INTERNAL_ERROR));//PurchaseError.PURCHASE_NOT_FOUND));
        BigDecimal total = purchaseItemRepository.calculatePurchaseTotal(purchaseId);
        purchase.setTotalAmount(total);
        purchaseRepository.save(purchase);
    }
}
