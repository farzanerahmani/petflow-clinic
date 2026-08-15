package com.roochi.petflowinventory.warehouse.service.command;

import com.roochi.petflowinventory.warehouse.dto.request.AddWarehouseRequestDto;
import com.roochi.petflowinventory.warehouse.dto.request.ChangeWarehouseStatusRequestDto;
import com.roochi.petflowinventory.warehouse.dto.request.UpdateWarehouseRequestDto;
import com.roochi.petflowinventory.warehouse.dto.response.AddWarehouseResponseDto;
import com.roochi.petflowinventory.warehouse.dto.response.ChangeWarehouseStatusResponseDto;
import com.roochi.petflowinventory.warehouse.dto.response.UpdateWarehouseResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
public interface WarehouseCommandService {

    AddWarehouseResponseDto addWarehouse(AddWarehouseRequestDto requestDto);

    UpdateWarehouseResponseDto updateWarehouse(UpdateWarehouseRequestDto requestDto);

    ChangeWarehouseStatusResponseDto changeWarehouseStatus(ChangeWarehouseStatusRequestDto requestDto);
}
