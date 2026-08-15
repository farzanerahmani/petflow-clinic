package com.roochi.petflowinventory.purchase.service.impl;

import com.roochi.petflowinventory.purchase.domain.PurchaseInventorySynchronizer;
import com.roochi.petflowinventory.purchase.dto.purchase.request.AddPurchaseRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchase.request.CancelPurchaseRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchase.request.CompletePurchaseRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchase.request.UpdatePurchaseRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.AddPurchaseResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.CancelPurchaseResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.CompletePurchaseResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.UpdatePurchaseResponseDto;
import com.roochi.petflowinventory.purchase.entity.Purchase;
import com.roochi.petflowinventory.purchase.entity.PurchaseItem;
import com.roochi.petflowinventory.purchase.entity.enums.PurchaseStatus;
import com.roochi.petflowinventory.purchase.repository.PurchaseItemRepository;
import com.roochi.petflowinventory.purchase.repository.PurchaseRepository;
import com.roochi.petflowinventory.purchase.service.command.PurchaseCommandService;
import com.roochi.petflowinventory.supplier.entity.Supplier;
import com.roochi.petflowinventory.supplier.repository.SupplierRepository;
import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowinventory.warehouse.repository.WarehouseRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseCommandServiceImpl implements PurchaseCommandService {

    private final PurchaseRepository purchaseRepository;
    private final SupplierRepository supplierRepository;

    private final PurchaseInventorySynchronizer purchaseInventorySynchronizer;

    private final PurchaseItemRepository purchaseItemRepository;

    private final WarehouseRepository warehouseRepository;

    @Override
    public AddPurchaseResponseDto addPurchase(AddPurchaseRequestDto requestDto) {
        purchaseRepository.findByPurchaseNumber(requestDto.getPurchaseNumber()).ifPresent(p -> {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);//PurchaseError.PURCHASE_NUMBER_ALREADY_EXISTS);
        });
        Warehouse warehouse = warehouseRepository.findById(requestDto.getWarehouseId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));

        Supplier supplier = supplierRepository.
                findById(requestDto.getSupplierId()).orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));
        //SupplierError.SUPPLIER_NOT_FOUND));
        Purchase purchase = Purchase.builder()
                .purchaseNumber(requestDto.getPurchaseNumber())
                .supplier(supplier)
                .warehouse(warehouse)
                .purchaseDate(requestDto.getPurchaseDate())
                .description(requestDto.getDescription())
                .status(PurchaseStatus.DRAFT)
                .totalAmount(BigDecimal.ZERO)
                .build();

        purchaseRepository.save(purchase);
        return AddPurchaseResponseDto.builder()
                .id(purchase.getId())
                .build();
    }

    @Override
    public UpdatePurchaseResponseDto updatePurchase(UpdatePurchaseRequestDto requestDto) {
        Purchase purchase = purchaseRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));
        //PurchaseError.PURCHASE_NOT_FOUND));
        if (purchase.getStatus() != PurchaseStatus.DRAFT) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);//PurchaseError.PURCHASE_CAN_NOT_BE_EDITED);
        }
        purchaseRepository.findByPurchaseNumber(requestDto.getPurchaseNumber())
                .filter(p -> !p.getId().equals(purchase.getId())).ifPresent(p -> {
                    throw new NotFoundException(ErrorCode.INTERNAL_ERROR);//PurchaseError.PURCHASE_NUMBER_ALREADY_EXISTS);
                });
        Supplier supplier = supplierRepository.findById(requestDto.getSupplierId()).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR));//SupplierError.SUPPLIER_NOT_FOUND));
        purchase.setPurchaseNumber(requestDto.getPurchaseNumber());
        purchase.setSupplier(supplier);
        purchase.setPurchaseDate(requestDto.getPurchaseDate());
        purchase.setDescription(requestDto.getDescription());
        purchaseRepository.save(purchase);
        return UpdatePurchaseResponseDto.builder()
                .id(purchase.getId())
                .build();
    }

    @Override
    public CompletePurchaseResponseDto CompletePurchase(CompletePurchaseRequestDto requestDto) {

        Purchase purchase = purchaseRepository.findByIdForUpdate(requestDto.getPurchaseId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));


        if (purchase.getStatus() != PurchaseStatus.DRAFT) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
        }


        List<PurchaseItem> purchaseItems =
                purchaseItemRepository.findAllByPurchaseId(purchase.getId());


        if (purchaseItems.isEmpty()) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
        }


        purchaseInventorySynchronizer.synchronize(
                purchase,
                purchaseItems,
                "system"
        );

        purchase.setStatus(PurchaseStatus.COMPLETED);


        purchaseRepository.save(purchase);


        return new CompletePurchaseResponseDto();
    }

    @Override
    public CancelPurchaseResponseDto CancelPurchase(CancelPurchaseRequestDto requestDto) {
        Purchase purchase = purchaseRepository.findByIdForUpdate(requestDto.getPurchaseId()).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR));
        //PurchaseError.PURCHASE_NOT_FOUND));
        if (purchase.getStatus() == PurchaseStatus.COMPLETED) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
            //PurchaseError.COMPLETED_PURCHASE_CAN_NOT_BE_CANCELLED);
        }
        purchase.setStatus(PurchaseStatus.CANCELLED);
        purchaseRepository.save(purchase);
        return new CancelPurchaseResponseDto();
    }
}
