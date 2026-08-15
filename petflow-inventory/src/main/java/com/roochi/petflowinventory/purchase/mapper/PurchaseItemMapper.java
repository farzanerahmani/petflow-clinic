package com.roochi.petflowinventory.purchase.mapper;


import com.roochi.petflowinventory.purchase.dto.purchaseitem.response.PurchaseItemResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchaseitem.response.PurchaseItemSummaryDto;
import com.roochi.petflowinventory.purchase.entity.PurchaseItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/28/2026
 */



@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {
    @Mapping(target = "purchaseId", source = "purchase.id")
    @Mapping(target = "drugId", source = "drug.id")
    @Mapping(target = "drugCode", source = "drug.code")
    @Mapping(target = "drugName", source = "drug.name")
    PurchaseItemResponseDto toResponseDto(PurchaseItem entity);

    @Mapping(target = "drugCode", source = "drug.code")
    @Mapping(target = "drugName", source = "drug.name")
    PurchaseItemSummaryDto toSummaryDto(PurchaseItem entity);

    List<PurchaseItemResponseDto> toResponseDtos(List<PurchaseItem> entities);

    List<PurchaseItemSummaryDto> toSummaryDtos(List<PurchaseItem> entities);
}
