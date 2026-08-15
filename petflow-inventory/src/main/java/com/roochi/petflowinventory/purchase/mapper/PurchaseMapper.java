package com.roochi.petflowinventory.purchase.mapper;

import com.roochi.petflowinventory.purchase.dto.purchase.response.PurchaseResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.PurchaseSummaryDto;
import com.roochi.petflowinventory.purchase.entity.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */


@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    @Mapping(target = "supplierId", source = "supplier.id")
    @Mapping(target = "supplierName", source = "supplier.name")
    PurchaseResponseDto toResponseDto(Purchase entity);

    @Mapping(target = "supplierName", source = "supplier.name")
    PurchaseSummaryDto toSummaryDto(Purchase entity);

    List<PurchaseResponseDto> toResponseDtos(List<Purchase> entities);

    List<PurchaseSummaryDto> toSummaryDtos(List<Purchase> entities);

}
