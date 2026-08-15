package com.roochi.petflowinventory.warehouse.service.impl;

import com.roochi.petflowinventory.warehouse.dto.request.AddWarehouseRequestDto;
import com.roochi.petflowinventory.warehouse.dto.request.ChangeWarehouseStatusRequestDto;
import com.roochi.petflowinventory.warehouse.dto.request.UpdateWarehouseRequestDto;
import com.roochi.petflowinventory.warehouse.dto.response.AddWarehouseResponseDto;
import com.roochi.petflowinventory.warehouse.dto.response.ChangeWarehouseStatusResponseDto;
import com.roochi.petflowinventory.warehouse.dto.response.UpdateWarehouseResponseDto;
import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowinventory.warehouse.repository.WarehouseRepository;
import com.roochi.petflowinventory.warehouse.service.command.WarehouseCommandService;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class WarehouseCommandServiceImpl implements WarehouseCommandService {
    private final WarehouseRepository warehouseRepository;

    @Override
    public AddWarehouseResponseDto addWarehouse(AddWarehouseRequestDto requestDto) {
        warehouseRepository.findByCode(requestDto.getCode())
                .ifPresent(w ->
                {
                    throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
                });
        //WarehouseError.WAREHOUSE_CODE_ALREADY_EXISTS); });
        if (Boolean.TRUE.equals(requestDto.getDefaultWarehouse())) {
            warehouseRepository.findDefaultWarehouse()
                    .ifPresent(defaultWarehouse ->
                    {
                        defaultWarehouse.setDefaultWarehouse(false);
                        warehouseRepository.save(defaultWarehouse);
                    });
        }
        Warehouse warehouse = Warehouse.builder()
                .code(requestDto.getCode())
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .active(Boolean.TRUE.equals(requestDto.getActive()))
                .defaultWarehouse(Boolean.TRUE.equals(requestDto.getDefaultWarehouse()))
                .build();
        warehouseRepository.save(warehouse);
        return AddWarehouseResponseDto.builder()
                .id(warehouse.getId())
                .build();
    }

    @Override
    public UpdateWarehouseResponseDto updateWarehouse(UpdateWarehouseRequestDto requestDto) {
        Warehouse warehouse = warehouseRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));
        // WarehouseError.WAREHOUSE_NOT_FOUND));
        warehouseRepository.findByCode(requestDto.getCode()).
                filter(w -> !w.getId().equals(warehouse.getId())).ifPresent(w -> {
                    throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
                    //WarehouseError.WAREHOUSE_CODE_ALREADY_EXISTS);
                });
        if (Boolean.TRUE.equals(requestDto.getDefaultWarehouse())) {
            warehouseRepository.findDefaultWarehouse().filter(w -> !w.getId().equals(warehouse.getId())).ifPresent(defaultWarehouse -> {
                defaultWarehouse.setDefaultWarehouse(false);
                warehouseRepository.save(defaultWarehouse);
            });
        }
        warehouse.setCode(requestDto.getCode());
        warehouse.setName(requestDto.getName());
        warehouse.setDescription(requestDto.getDescription());
        warehouse.setActive(requestDto.getActive());
        warehouse.setDefaultWarehouse(requestDto.getDefaultWarehouse());
        warehouseRepository.save(warehouse);
        return UpdateWarehouseResponseDto.builder()
                .id(warehouse.getId())
                .build();
    }

    @Override
    public ChangeWarehouseStatusResponseDto changeWarehouseStatus(ChangeWarehouseStatusRequestDto requestDto) {
        Warehouse warehouse = warehouseRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR));//WarehouseError.WAREHOUSE_NOT_FOUND));
        if (Boolean.TRUE.equals(warehouse.getDefaultWarehouse())) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
            //WarehouseError.DEFAULT_WAREHOUSE_CAN_NOT_BE_DELETED);
        }
        warehouse.setActive(!warehouse.getActive());

        warehouseRepository.save(warehouse);
        return new ChangeWarehouseStatusResponseDto();
    }
}
