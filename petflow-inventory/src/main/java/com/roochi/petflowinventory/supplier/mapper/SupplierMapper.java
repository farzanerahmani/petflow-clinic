package com.roochi.petflowinventory.supplier.mapper;

import com.roochi.petflowinventory.supplier.dto.response.SupplierResponseDto;
import com.roochi.petflowinventory.supplier.dto.response.SupplierSummaryDto;
import com.roochi.petflowinventory.supplier.entity.Supplier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierResponseDto toResponseDto(Supplier entity);

    SupplierSummaryDto toSummaryDto(Supplier entity);

    List<SupplierResponseDto> toResponseDtos(List<Supplier> entities);

    List<SupplierSummaryDto> toSummaryDtos(List<Supplier> entities);
}
