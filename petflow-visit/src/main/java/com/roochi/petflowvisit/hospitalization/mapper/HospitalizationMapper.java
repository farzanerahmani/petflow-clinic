package com.roochi.petflowvisit.hospitalization.mapper;

import com.roochi.petflowvisit.dto.cmmon.HospitalizationDto;
import com.roochi.petflowvisit.dto.response.hospitalization.HospitalizationResponseDto;
import com.roochi.petflowvisit.hospitalization.entity.Hospitalization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */


@Mapper(componentModel = "spring")
public interface HospitalizationMapper {

    @Mapping(target = "visitId", source = "visit.id")
    @Mapping(target = "attendingVeterinarianId", source = "attendingVeterinarian.id")
    @Mapping(target = "attendingVeterinarianName", source = "attendingVeterinarian.fullName")
    HospitalizationResponseDto toResponseDto(Hospitalization entity);

    @Mapping(target = "attendingVeterinarianName", source = "attendingVeterinarian.fullName")
    HospitalizationDto toHospitalizationDto(Hospitalization entity);

    List<HospitalizationResponseDto> toResponseDtos(List<Hospitalization> entities);

    List<HospitalizationDto> toHospitalizationDtos(List<Hospitalization> entities);

}
