package com.roochi.petflowinventory.warehouse.service.impl;

import com.roochi.petflowinventory.warehouse.dto.request.GetWarehouseByIdRequestDto;
import com.roochi.petflowinventory.warehouse.dto.request.GetWarehouseForUpdateRequestDto;
import com.roochi.petflowinventory.warehouse.dto.request.SearchWarehouseRequestDto;
import com.roochi.petflowinventory.warehouse.dto.response.SearchWarehouseResponseDto;
import com.roochi.petflowinventory.warehouse.dto.response.WarehouseResponseDto;
import com.roochi.petflowinventory.warehouse.dto.response.WarehouseSummaryDto;
import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowinventory.warehouse.mapper.WarehouseMapper;
import com.roochi.petflowinventory.warehouse.repository.WarehouseRepository;
import com.roochi.petflowinventory.warehouse.service.query.WarehouseQueryService;
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
 * @created 7/27/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WarehouseQueryServiceImpl implements WarehouseQueryService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    @Override
    public WarehouseResponseDto getWarehouseById(GetWarehouseByIdRequestDto requestDto) {
        Warehouse warehouse = warehouseRepository.findById(requestDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));//WarehouseError.WAREHOUSE_NOT_FOUND));
        return warehouseMapper.toResponseDto(warehouse);
    }

    @Override
    public WarehouseResponseDto getWarehouseForUpdate(GetWarehouseForUpdateRequestDto requestDto) {
        Warehouse warehouse = warehouseRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));//WarehouseError.WAREHOUSE_NOT_FOUND));
        return warehouseMapper.toResponseDto(warehouse);
    }

    @Override
    public SearchWarehouseResponseDto searchWarehouse(SearchWarehouseRequestDto requestDto) {



        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));
        Page<Warehouse> page = warehouseRepository.search( requestDto.getCode(),
                requestDto.getName(),
                requestDto.getActive(),
                pageRequest);

        List<WarehouseSummaryDto> warehouses =
                page.getContent()
                        .stream()
                        .map(warehouseMapper::toSummaryDto)
                        .toList();

        SearchWarehouseResponseDto response = new SearchWarehouseResponseDto();
        response.setResults(warehouses);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
