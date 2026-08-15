package com.roochi.petflowinventory.inventoryread.service;

import com.roochi.petflowinventory.stock.dto.request.SearchStockRequestDto;
import com.roochi.petflowinventory.stock.dto.response.SearchStockResponseDto;
import com.roochi.petflowinventory.stock.entity.Stock;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
public interface InventoryReadService {

    Page<Stock> searchStock(SearchStockRequestDto requestDto);

    List<Stock> findLowStocks();

    List<Stock> findExpiredStocks();

    List<Stock> findNearExpirationStocks(int days);

    List<Stock> findAvailableStocks(Long warehouseId, Long drugId);

}
