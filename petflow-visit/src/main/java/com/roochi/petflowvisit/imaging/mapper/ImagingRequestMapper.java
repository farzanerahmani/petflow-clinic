package com.roochi.petflowvisit.imaging.mapper;

import com.roochi.petflowvisit.dto.cmmon.ImagingRequestDto;
import com.roochi.petflowvisit.dto.response.imaging.ImagingRequestResponseDto;
import com.roochi.petflowvisit.imaging.entity.ImagingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */


@Mapper(componentModel = "spring")
public interface ImagingRequestMapper {

    @Mapping(target = "visitId", source = "visit.id")
    @Mapping(target = "imagingServiceId", source = "imagingService.id")
    @Mapping(target = "imagingServiceName", source = "imagingService.name")
    @Mapping(target = "hasResult",
            expression = "java(entity.getImagingResult() != null)")
    ImagingRequestResponseDto toResponseDto(ImagingRequest entity);

    @Mapping(target = "imagingServiceName", source = "imagingService.name")
    @Mapping(target = "hasResult",
            expression = "java(entity.getImagingResult() != null)")
    ImagingRequestDto toImagingRequestDto(ImagingRequest entity);

    List<ImagingRequestResponseDto> toResponseDtos(List<ImagingRequest> entities);

    List<ImagingRequestDto> toImagingRequestDtos(List<ImagingRequest> entities);
}
