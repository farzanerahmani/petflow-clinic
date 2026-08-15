package com.roochi.petflowinventory.sale.service.impl;

import com.roochi.petflowinventory.sale.dto.request.SaleSearchRequestDto;
import com.roochi.petflowinventory.sale.dto.response.SaleResponseDto;
import com.roochi.petflowinventory.sale.dto.response.SearchSaleResponseDto;
import com.roochi.petflowinventory.sale.entity.Sale;
import com.roochi.petflowinventory.sale.repository.SaleRepository;
import com.roochi.petflowinventory.sale.service.quesry.SaleQueryService;
import com.roochi.petflowinventory.sale.specification.SaleSpecification;
import com.roochi.petflowinventory.warehouse.dto.response.SearchWarehouseResponseDto;
import com.roochi.petflowinventory.warehouse.dto.response.WarehouseSummaryDto;
import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/3/2026
 */


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaleQueryServiceImpl implements SaleQueryService {

    private final SaleRepository saleRepository;

    @Override
    public SaleResponseDto findById(Long id) {

        Sale sale = saleRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));

        return mapToDto(sale);
    }

    @Override
    public SearchSaleResponseDto searchSale(SaleSearchRequestDto requestDto) {
        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<Sale> page = saleRepository.findAll(
                        SaleSpecification.search(requestDto),pageRequest);

        List<SaleResponseDto> sales =
                page.getContent()
                        .stream()
                        .map(this::mapToDto)
                        .toList();

        SearchSaleResponseDto response = new SearchSaleResponseDto();
        response.setResults(sales);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }

    private SaleResponseDto mapToDto(Sale sale) {

        return SaleResponseDto.builder()
                .id(sale.getId())

                .saleNumber(sale.getSaleNumber())

                .warehouseId(
                        sale.getWarehouse().getId()
                )

                .warehouseName(
                        sale.getWarehouse().getName()
                )

                .customerId(
                        sale.getCustomer() != null ?
                                sale.getCustomer().getId() : null
                )

                .customerName(
                        sale.getCustomer() != null ?
                                sale.getCustomer().toString() : null
                )

                .saleDate(
                        sale.getSaleDate()
                )

                .status(
                        sale.getStatus()
                )

                .totalAmount(
                        sale.getTotalAmount()
                )

                .description(
                        sale.getDescription()
                )

                .build();
    }

}
