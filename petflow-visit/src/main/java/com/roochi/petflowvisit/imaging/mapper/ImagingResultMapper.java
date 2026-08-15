package com.roochi.petflowvisit.imaging.mapper;

import com.roochi.petflowvisit.dto.cmmon.ImagingResultDto;
import com.roochi.petflowvisit.dto.response.imaging.ImagingResultResponseDto;
import com.roochi.petflowvisit.imaging.entity.ImagingResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/23/2026
 */


@Mapper(componentModel = "spring")
public interface ImagingResultMapper {

    @Mapping(target = "imagingRequestId", source = "imagingRequest.id")
    ImagingResultResponseDto toResponseDto(ImagingResult entity);

    ImagingResultDto toImagingResultDto(ImagingResult entity);

    List<ImagingResultResponseDto> toResponseDtos(
            List<ImagingResult> entities);

    List<ImagingResultDto> toImagingResultDtos(
            List<ImagingResult> entities);
}
