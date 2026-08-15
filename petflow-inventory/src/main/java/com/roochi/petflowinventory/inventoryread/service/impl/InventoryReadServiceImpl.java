package com.roochi.petflowinventory.inventoryread.service.impl;

import com.roochi.petflowinventory.inventoryread.service.InventoryReadService;
import com.roochi.petflowinventory.stock.dto.request.SearchStockRequestDto;
import com.roochi.petflowinventory.stock.dto.response.SearchStockResponseDto;
import com.roochi.petflowinventory.stock.dto.response.StockResponseDto;
import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stock.repository.StockRepository;
import com.roochi.petflowinventory.stock.specification.StockSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
@Service
@RequiredArgsConstructor
public class InventoryReadServiceImpl implements InventoryReadService {

    private final StockRepository repository;

    @Override
    public Page<Stock> searchStock(SearchStockRequestDto requestDto){
        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

       return repository.findAll(
                StockSpecification.search(requestDto),
                pageRequest);

    }


    @Override
    public List<Stock> findLowStocks() {

        return repository.findLowStocks();
    }

    @Override
    public List<Stock> findExpiredStocks() {

        return repository.findExpiredStocks(
                LocalDate.now()
        );
    }

    @Override
    public List<Stock> findNearExpirationStocks(
            int days) {

        LocalDate today = LocalDate.now();

        return repository.findNearExpirationStocks(
                today,
                today.plusDays(days)
        );
    }

    @Override
    public List<Stock> findAvailableStocks(
            Long warehouseId,
            Long drugId) {

        return repository.findAvailableStocksOrderByExpirationForUpdate(
                warehouseId,
                drugId
        );
    }
}
