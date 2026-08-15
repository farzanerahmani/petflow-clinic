package com.roochi.petflowvisit.labrequest.mapper;

import com.roochi.petflowvisit.dto.cmmon.LabRequestDto;
import com.roochi.petflowvisit.dto.cmmon.VaccinationDto;
import com.roochi.petflowvisit.dto.response.labrequest.LabRequestResponseDto;
import com.roochi.petflowvisit.dto.response.vaccination.VaccinationResponseDto;
import com.roochi.petflowvisit.labrequest.entity.LabRequest;
import com.roochi.petflowvisit.vaccination.entity.Vaccination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/21/2026
 */

@Mapper(componentModel = "spring")
public interface LabRequestMapper {

    @Mapping(target = "visitId", source = "visit.id")
    @Mapping(target = "labTestId", source = "labTest.id")
    @Mapping(target = "labTestCode", source = "labTest.code")
    @Mapping(target = "labTestName", source = "labTest.name")
    LabRequestResponseDto toResponseDto(LabRequest labRequest);

    @Mapping(target = "labTestName", source = "labTest.name")
    LabRequestDto toLabRequestDto(LabRequest labRequest);

    List<LabRequestDto> toLabRequestDtos(List<LabRequest> labRequests);

    List<LabRequestResponseDto> toResponseDtos(List<LabRequest> labRequests);
}

