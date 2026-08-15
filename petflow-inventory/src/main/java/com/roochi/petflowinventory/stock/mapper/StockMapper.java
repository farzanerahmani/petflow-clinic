package com.roochi.petflowinventory.stock.mapper;

import com.roochi.petflowinventory.stock.dto.response.StockResponseDto;
import com.roochi.petflowinventory.stock.dto.response.StockSummaryDto;
import com.roochi.petflowinventory.stock.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */


@Mapper(componentModel = "spring")
public interface StockMapper {
    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "warehouseName", source = "warehouse.name")
    @Mapping(target = "drugId", source = "drug.id")
    @Mapping(target = "drugCode", source = "drug.code")
    @Mapping(target = "drugName", source = "drug.name")
    @Mapping(target = "availableQuantity", expression = "java(stock.getQuantity().subtract(stock.getReservedQuantity()))")
    StockResponseDto toResponseDto(Stock stock);

    @Mapping(target = "warehouseName", source = "warehouse.name")
    @Mapping(target = "drugCode", source = "drug.code")
    @Mapping(target = "drugName", source = "drug.name")
    StockSummaryDto toSummaryDto(Stock stock);

    List<StockResponseDto> toResponseDtos(List<Stock> stocks);

    List<StockSummaryDto> toSummaryDtos(List<Stock> stocks);
}
