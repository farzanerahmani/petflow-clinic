package com.roochi.petflowinventory.stock.controller;

import com.roochi.petflowinventory.stock.dto.request.SearchStockRequestDto;
import com.roochi.petflowinventory.stock.dto.response.SearchStockResponseDto;
import com.roochi.petflowinventory.stock.dto.response.StockResponseDto;
import com.roochi.petflowinventory.stock.service.query.StockQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {


    private final StockQueryService stockQueryService;


    @GetMapping
    public SearchStockResponseDto search(
            SearchStockRequestDto request) {

        return stockQueryService.searchStock(
                request
        );
    }


    @GetMapping("/low-stock")
    public List<StockResponseDto> lowStock() {

        return stockQueryService.findLowStocks();
    }


    @GetMapping("/expired")
    public List<StockResponseDto> expired() {

        return stockQueryService.findExpiredStocks();
    }


    @GetMapping("/near-expiration")
    public List<StockResponseDto> nearExpiration(
            @RequestParam(defaultValue = "30")
            int days) {

        return stockQueryService
                .findNearExpirationStocks(days);
    }
}
