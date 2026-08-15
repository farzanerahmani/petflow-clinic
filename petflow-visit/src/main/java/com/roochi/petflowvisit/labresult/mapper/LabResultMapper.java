package com.roochi.petflowvisit.labresult.mapper;

import com.roochi.petflowvisit.dto.cmmon.LabResultDto;
import com.roochi.petflowvisit.dto.response.labresult.LabResultResponseDto;
import com.roochi.petflowvisit.labresult.entity.LabResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */

@Mapper(
        componentModel = "spring",
        uses = LabResultItemMapper.class
)
public interface LabResultMapper {

    @Mapping(target = "labRequestId", source = "labRequest.id")
    LabResultResponseDto toResponseDto(LabResult entity);

    LabResultDto toLabResultDto(LabResult entity);

    List<LabResultResponseDto> toResponseDtos(List<LabResult> entities);

    List<LabResultDto> toLabResultDtos(List<LabResult> entities);
}
