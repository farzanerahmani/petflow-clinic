package com.roochi.petflowinventory.stock.service.query;

import com.roochi.petflowinventory.stock.dto.request.SearchStockRequestDto;
import com.roochi.petflowinventory.stock.dto.response.SearchStockResponseDto;
import com.roochi.petflowinventory.stock.dto.response.StockResponseDto;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/1/2026
 */
public interface StockQueryService {

    SearchStockResponseDto searchStock(SearchStockRequestDto request);


    List<StockResponseDto> findLowStocks();

    List<StockResponseDto> findExpiredStocks();

    List<StockResponseDto> findNearExpirationStocks(int days);

}
