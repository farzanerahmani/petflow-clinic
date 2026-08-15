package com.roochi.petflowinventory.warehouse.service.query;

import com.roochi.petflowinventory.warehouse.dto.request.GetWarehouseByIdRequestDto;
import com.roochi.petflowinventory.warehouse.dto.request.GetWarehouseForUpdateRequestDto;
import com.roochi.petflowinventory.warehouse.dto.request.SearchWarehouseRequestDto;
import com.roochi.petflowinventory.warehouse.dto.response.SearchWarehouseResponseDto;
import com.roochi.petflowinventory.warehouse.dto.response.WarehouseResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
public interface WarehouseQueryService {

    WarehouseResponseDto getWarehouseById(GetWarehouseByIdRequestDto requestDto);

    WarehouseResponseDto getWarehouseForUpdate(GetWarehouseForUpdateRequestDto requestDto);

    SearchWarehouseResponseDto searchWarehouse(SearchWarehouseRequestDto requestDto);
}
