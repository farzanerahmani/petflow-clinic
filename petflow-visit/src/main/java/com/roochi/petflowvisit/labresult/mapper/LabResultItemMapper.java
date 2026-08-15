package com.roochi.petflowvisit.labresult.mapper;

import com.roochi.petflowvisit.dto.cmmon.LabResultItemDto;
import com.roochi.petflowvisit.dto.response.labresult.LabResultItemResponseDto;
import com.roochi.petflowvisit.labresult.entity.LabResultItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */

@Mapper(componentModel = "spring")
public interface LabResultItemMapper {

    @Mapping(target = "labTestParameterId", source = "labTestParameter.id")
    @Mapping(target = "parameterName", source = "labTestParameter.name")
    LabResultItemResponseDto toResponseDto(LabResultItem entity);

    @Mapping(target = "parameterName", source = "labTestParameter.name")
    LabResultItemDto toLabResultItemDto(LabResultItem entity);

    List<LabResultItemResponseDto> toResponseDtos(List<LabResultItem> entities);

    List<LabResultItemDto> toLabResultItemDtos(List<LabResultItem> entities);
}
