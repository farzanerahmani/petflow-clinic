package com.roochi.petflowvisit.imaging.mapper;

import com.roochi.petflowvisit.dto.cmmon.ImagingServiceDto;
import com.roochi.petflowvisit.dto.response.imaging.ImagingServiceResponseDto;
import com.roochi.petflowvisit.imaging.entity.ImagingService;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Mapper(componentModel = "spring")
public interface ImagingServiceMapper {

    ImagingServiceResponseDto toResponseDto(ImagingService entity);

    ImagingServiceDto toImagingServiceDto(ImagingService entity);

    List<ImagingServiceResponseDto> toResponseDtos(List<ImagingService> entities);

    List<ImagingServiceDto> toImagingServiceDtos(List<ImagingService> entities);
}
