package com.roochi.petflowinventory.stocktransaction.service.impl;


import com.roochi.petflowinventory.stocktransaction.dto.response.StockTransactionResponseDto;
import com.roochi.petflowinventory.stocktransaction.entity.StockTransaction;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockReferenceType;
import com.roochi.petflowinventory.stocktransaction.repository.StockTransactionRepository;
import com.roochi.petflowinventory.stocktransaction.service.query.StockTransactionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockTransactionQueryServiceImpl implements StockTransactionQueryService {


    private final StockTransactionRepository repository;


    @Override
    public List<StockTransactionResponseDto> findByStock(
            Long stockId) {

        return repository.findAllByStockId(stockId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }


    @Override
    public List<StockTransactionResponseDto> findByReference(
            StockReferenceType referenceType,
            Long referenceId) {

        return repository
                .findAllByReference(
                        referenceType,
                        referenceId
                )
                .stream()
                .map(this::mapToDto)
                .toList();
    }


    @Override
    public List<StockTransactionResponseDto> findByDrug(
            Long drugId) {

        return repository.findAllByDrugId(drugId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }


    @Override
    public List<StockTransactionResponseDto> findByWarehouse(
            Long warehouseId) {

        return repository.findAllByWarehouseId(warehouseId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }


    private StockTransactionResponseDto mapToDto(
            StockTransaction st) {


        return StockTransactionResponseDto.builder()

                .id(st.getId())

                .stockId(
                        st.getStock().getId()
                )

                .drugId(
                        st.getStock()
                                .getDrug()
                                .getId()
                )

                .drugName(
                        st.getStock()
                                .getDrug()
                                .getBrandName()
                )

                .batchNumber(
                        st.getStock()
                                .getBatchNumber()
                )

                .warehouseId(
                        st.getStock()
                                .getWarehouse()
                                .getId()
                )

                .warehouseName(
                        st.getStock()
                                .getWarehouse()
                                .getName()
                )

                .transactionType(
                        st.getTransactionType()
                )

                .referenceType(
                        st.getReferenceType()
                )

                .referenceId(
                        st.getReferenceId()
                )

                .referenceNumber(
                        st.getReferenceNumber()
                )

                .quantity(
                        st.getQuantity()
                )

                .quantityBefore(
                        st.getQuantityBefore()
                )

                .quantityAfter(
                        st.getQuantityAfter()
                )

                .reservedQuantityBefore(
                        st.getReservedQuantityBefore()
                )

                .reservedQuantityAfter(
                        st.getReservedQuantityAfter()
                )

                .unitCost(
                        st.getUnitCost()
                )

                .description(
                        st.getDescription()
                )

                .createdBy(
                        st.getCreatedBy()
                )

                .createdAt(
                        st.getCreatedAt()
                )

                .build();
    }
}
