package com.roochi.petflowinventory.stock.service.impl;

import com.roochi.petflowinventory.inventoryread.service.InventoryReadService;
import com.roochi.petflowinventory.stock.dto.request.SearchStockRequestDto;
import com.roochi.petflowinventory.stock.dto.response.SearchStockResponseDto;
import com.roochi.petflowinventory.stock.dto.response.StockResponseDto;
import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stock.mapper.StockMapper;
import com.roochi.petflowinventory.stock.repository.StockRepository;
import com.roochi.petflowinventory.stock.service.query.StockQueryService;
import com.roochi.petflowinventory.stock.specification.StockSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/1/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockQueryServiceImpl implements StockQueryService {

    private final InventoryReadService inventoryReadService;


    @Override
    public SearchStockResponseDto searchStock(SearchStockRequestDto requestDto) {


        Page<Stock> page = inventoryReadService.searchStock(requestDto);
        List<StockResponseDto> stocks =
                page.getContent()
                        .stream()
                        .map(this::mapToDto)
                        .toList();

        SearchStockResponseDto response = new SearchStockResponseDto();
        response.setResults(stocks);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }


    @Override
    public List<StockResponseDto> findLowStocks() {

        return inventoryReadService.findLowStocks()
                .stream()
                .map(this::mapToDto)
                .toList();
    }


    @Override
    public List<StockResponseDto> findExpiredStocks() {

        return inventoryReadService.findExpiredStocks()
                .stream()
                .map(this::mapToDto)
                .toList();
    }


    @Override
    public List<StockResponseDto> findNearExpirationStocks(int days) {
        return inventoryReadService
                .findNearExpirationStocks(days)
                .stream()
                .map(this::mapToDto)
                .toList();
    }


    private StockResponseDto mapToDto(
            Stock stock) {


        return StockResponseDto.builder()

                .id(stock.getId())

                .warehouseId(
                        stock.getWarehouse().getId()
                )

                .warehouseName(
                        stock.getWarehouse().getName()
                )

                .drugId(
                        stock.getDrug().getId()
                )

                .drugName(
                        stock.getDrug().getBrandName()
                )

                .batchNumber(
                        stock.getBatchNumber()
                )

                .expirationDate(
                        stock.getExpirationDate()
                )

                .quantity(
                        stock.getQuantity()
                )

                .reservedQuantity(
                        stock.getReservedQuantity()
                )

                .availableQuantity(
                        stock.getAvailableQuantity()
                )

                .minimumQuantity(
                        stock.getMinimumQuantity()
                )

                .averageUnitCost(
                        stock.getAverageUnitCost()
                )

                .build();
    }
}
