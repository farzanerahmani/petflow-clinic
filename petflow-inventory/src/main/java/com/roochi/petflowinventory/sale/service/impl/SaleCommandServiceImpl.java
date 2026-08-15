package com.roochi.petflowinventory.sale.service.impl;

import com.roochi.petflowinventory.sale.dto.request.*;
import com.roochi.petflowinventory.sale.dto.response.*;
import com.roochi.petflowinventory.sale.entity.*;
import com.roochi.petflowinventory.sale.entity.enums.SaleStatus;
import com.roochi.petflowinventory.sale.repository.*;
import com.roochi.petflowinventory.sale.service.command.SaleCommandService;
import com.roochi.petflowinventory.sale.service.support.SaleInventorySynchronizer;
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
 * @created 8/2/2026
 */


@Service
@RequiredArgsConstructor
@Transactional
public class SaleCommandServiceImpl implements SaleCommandService {

    private final SaleRepository saleRepository;

    private final SaleItemRepository saleItemRepository;

    private final WarehouseRepository warehouseRepository;

    private final SaleInventorySynchronizer saleInventorySynchronizer;

    @Override
    public AddSaleResponseDto addSale(AddSaleRequestDto requestDto) {

        saleRepository.findBySaleNumber(requestDto.getSaleNumber())
                .ifPresent(s -> {
                    throw new NotFoundException(ErrorCode.CLINIC_NOT_FOUND);//.INTERNAL_ERROR);
                });

        Warehouse warehouse =
                warehouseRepository.findById(requestDto.getWarehouseId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR));

        Sale sale = Sale.builder()
                .saleNumber(requestDto.getSaleNumber())
                .warehouse(warehouse)
                .saleDate(requestDto.getSaleDate())
                .description(requestDto.getDescription())
                .status(SaleStatus.DRAFT)
                .totalAmount(BigDecimal.ZERO)
                .build();

        saleRepository.save(sale);

        return AddSaleResponseDto.builder()
                .id(sale.getId())
                .build();
    }

    @Override
    public UpdateSaleResponseDto updateSale(UpdateSaleRequestDto requestDto) {

        Sale sale =
                saleRepository.findByIdForUpdate(requestDto.getId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR));

        if (sale.getStatus() != SaleStatus.DRAFT) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
        }

        saleRepository.findBySaleNumber(requestDto.getSaleNumber())
                .filter(s -> !s.getId().equals(sale.getId()))
                .ifPresent(s -> {
                    throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
                });

        Warehouse warehouse =
                warehouseRepository.findById(requestDto.getWarehouseId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR));

        sale.setSaleNumber(requestDto.getSaleNumber());
        sale.setWarehouse(warehouse);
        sale.setSaleDate(requestDto.getSaleDate());
        sale.setDescription(requestDto.getDescription());

        saleRepository.save(sale);

        return UpdateSaleResponseDto.builder()
                .id(sale.getId())
                .build();
    }

    @Override
    public CompleteSaleResponseDto completeSale(
            CompleteSaleRequestDto requestDto) {

        Sale sale =
                saleRepository.findByIdForUpdate(requestDto.getSaleId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR));

        if (sale.getStatus() != SaleStatus.DRAFT) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
        }

        List<SaleItem> items =
                saleItemRepository.findAllBySaleId(sale.getId());

        saleInventorySynchronizer.synchronize(
                sale,
                items,
                "system"
        );

        sale.setStatus(SaleStatus.COMPLETED);

        saleRepository.save(sale);

        return CompleteSaleResponseDto.builder()
                .saleId(sale.getId())
                .message("Sale completed successfully.")
                .build();
    }

    @Override
    public CancelSaleResponseDto cancelSale(
            CancelSaleRequestDto requestDto) {

        Sale sale =
                saleRepository.findByIdForUpdate(requestDto.getSaleId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR));

        if (sale.getStatus() == SaleStatus.COMPLETED) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
        }

        sale.setStatus(SaleStatus.CANCELLED);

        saleRepository.save(sale);

        return CancelSaleResponseDto.builder()
                .saleId(sale.getId())
                .message("Sale cancelled successfully.")
                .build();
    }
}
