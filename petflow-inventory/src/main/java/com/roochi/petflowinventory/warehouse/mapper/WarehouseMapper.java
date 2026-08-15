package com.roochi.petflowinventory.warehouse.mapper;

import com.roochi.petflowinventory.warehouse.dto.response.WarehouseResponseDto;
import com.roochi.petflowinventory.warehouse.dto.response.WarehouseSummaryDto;
import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowshared.mapper.BaseMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
@Mapper(componentModel = "spring")
public interface WarehouseMapper extends BaseMapper<Warehouse, WarehouseSummaryDto>{
    WarehouseResponseDto toResponseDto(Warehouse entity);

    WarehouseSummaryDto toSummaryDto(Warehouse entity);

    List<WarehouseResponseDto> toResponseDtos(List<Warehouse> entities);

    List<WarehouseSummaryDto> toSummaryDtos(List<Warehouse> entities);

}
