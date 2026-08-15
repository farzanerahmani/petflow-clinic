package com.roochi.petflowinventory.purchase.service.impl;

import com.roochi.petflowinventory.purchase.dto.purchaseitem.request.AddPurchaseItemRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchaseitem.request.DeletePurchaseItemRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchaseitem.request.UpdatePurchaseItemRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchaseitem.response.AddPurchaseItemResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchaseitem.response.DeletePurchaseItemResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchaseitem.response.UpdatePurchaseItemResponseDto;
import com.roochi.petflowinventory.purchase.entity.Purchase;
import com.roochi.petflowinventory.purchase.entity.PurchaseItem;
import com.roochi.petflowinventory.purchase.entity.enums.PurchaseStatus;
import com.roochi.petflowinventory.purchase.repository.PurchaseItemRepository;
import com.roochi.petflowinventory.purchase.repository.PurchaseRepository;
import com.roochi.petflowinventory.purchase.service.command.PurchaseItemCommandService;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.drug.repository.DrugRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 7/28/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseItemCommandServiceImpl implements PurchaseItemCommandService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final DrugRepository drugRepository;
    private final PurchaseTotalCalculator purchaseTotalCalculator;

    @Override
    public AddPurchaseItemResponseDto addPurchaseItem(AddPurchaseItemRequestDto requestDto) {
        Purchase purchase = purchaseRepository.findByIdForUpdate(requestDto.getPurchaseId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));//PurchaseError.PURCHASE_NOT_FOUND));
        if (purchase.getStatus() != PurchaseStatus.DRAFT) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);//PurchaseError.PURCHASE_CAN_NOT_BE_EDITED);
        }
        Drug drug = drugRepository.findById(requestDto.getDrugId()).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR));//DrugError.DRUG_NOT_FOUND));
        BigDecimal lineTotal = requestDto.getQuantity().multiply(requestDto.getUnitPrice());
        PurchaseItem item = PurchaseItem.builder()
                .purchase(purchase)
                .drug(drug)
                .quantity(requestDto.getQuantity())
                .unitPrice(requestDto.getUnitPrice())
                .lineTotal(lineTotal)
                .batchNumber(requestDto.getBatchNumber())
                .expirationDate(requestDto.getExpirationDate())
                .build();
        purchaseItemRepository.save(item);
        purchaseTotalCalculator.recalculate(purchase.getId());
        return AddPurchaseItemResponseDto.builder()
                .id(item.getId()).build();
    }

    @Override
    public UpdatePurchaseItemResponseDto updatePurchaseItem(UpdatePurchaseItemRequestDto requestDto) {
        PurchaseItem item = purchaseItemRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));
        //PurchaseItemError.PURCHASE_ITEM_NOT_FOUND));
        if (item.getPurchase().getStatus() != PurchaseStatus.DRAFT) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);//PurchaseError.PURCHASE_CAN_NOT_BE_EDITED);
        }
        Drug drug = drugRepository.findById(requestDto.getDrugId()).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR));//DrugError.DRUG_NOT_FOUND));
        item.setDrug(drug);
        item.setQuantity(requestDto.getQuantity());
        item.setUnitPrice(requestDto.getUnitPrice());
        item.setLineTotal(requestDto.getQuantity().multiply(requestDto.getUnitPrice()));
        item.setBatchNumber(requestDto.getBatchNumber());
        item.setExpirationDate(requestDto.getExpirationDate());
        purchaseItemRepository.save(item);
        purchaseTotalCalculator.recalculate(item.getPurchase().getId());
        return UpdatePurchaseItemResponseDto.builder()
                .id(item.getId()).build();
    }

    @Override
    public DeletePurchaseItemResponseDto deletePurchaseItem(DeletePurchaseItemRequestDto requestDto) {
        PurchaseItem item = purchaseItemRepository.findByIdForUpdate(requestDto.getId()).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR));//PurchaseItemError.PURCHASE_ITEM_NOT_FOUND));
        if (item.getPurchase().getStatus() != PurchaseStatus.DRAFT) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);//PurchaseError.PURCHASE_CAN_NOT_BE_EDITED);
        }
        Long purchaseId = item.getPurchase().getId();
        purchaseItemRepository.delete(item);
        purchaseTotalCalculator.recalculate(purchaseId);
        return new DeletePurchaseItemResponseDto();
    }
}
