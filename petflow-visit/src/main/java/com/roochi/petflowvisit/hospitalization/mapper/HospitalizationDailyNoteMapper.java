package com.roochi.petflowvisit.hospitalization.mapper;

import com.roochi.petflowvisit.dto.cmmon.HospitalizationDailyNoteDto;
import com.roochi.petflowvisit.dto.response.hospitalization.HospitalizationDailyNoteResponseDto;
import com.roochi.petflowvisit.hospitalization.entity.HospitalizationDailyNote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */


import java.util.List;

@Mapper(componentModel = "spring")
public interface HospitalizationDailyNoteMapper {

    @Mapping(target = "hospitalizationId", source = "hospitalization.id")
    @Mapping(target = "veterinarianId", source = "veterinarian.id")
    @Mapping(target = "veterinarianName", source = "veterinarian.fullName")
    HospitalizationDailyNoteResponseDto toResponseDto(
            HospitalizationDailyNote entity);

    @Mapping(target = "veterinarianName", source = "veterinarian.fullName")
    HospitalizationDailyNoteDto toHospitalizationDailyNoteDto(
            HospitalizationDailyNote entity);

    List<HospitalizationDailyNoteResponseDto> toResponseDtos(
            List<HospitalizationDailyNote> entities);

    List<HospitalizationDailyNoteDto> toHospitalizationDailyNoteDtos(
            List<HospitalizationDailyNote> entities);

}
