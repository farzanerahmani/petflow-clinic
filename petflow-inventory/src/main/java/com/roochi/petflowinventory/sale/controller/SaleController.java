package com.roochi.petflowinventory.sale.controller;

import com.roochi.petflowinventory.sale.dto.request.*;
import com.roochi.petflowinventory.sale.dto.response.*;
import com.roochi.petflowinventory.sale.service.command.SaleCommandService;
import com.roochi.petflowinventory.sale.service.quesry.SaleQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author farzane.rahmani
 * @created 8/3/2026
 */


@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleCommandService commandService;

    private final SaleQueryService queryService;

    @PostMapping
    public AddSaleResponseDto add(
            @Valid @RequestBody AddSaleRequestDto request) {

        return commandService.addSale(request);
    }

    @PutMapping
    public UpdateSaleResponseDto update(
            @Valid @RequestBody UpdateSaleRequestDto request) {

        return commandService.updateSale(request);
    }

    @PostMapping("/complete")
    public CompleteSaleResponseDto complete(
            @Valid @RequestBody CompleteSaleRequestDto request) {

        return commandService.completeSale(request);
    }

    @PostMapping("/cancel")
    public CancelSaleResponseDto cancel(
            @Valid @RequestBody CancelSaleRequestDto request) {

        return commandService.cancelSale(request);
    }

    @GetMapping("/{id}")
    public SaleResponseDto findById(
            @PathVariable Long id) {

        return queryService.findById(id);
    }

    @PostMapping("/search")
    public SearchSaleResponseDto search(
            @RequestBody SaleSearchRequestDto request) {

        return queryService.searchSale(request);
    }

}
