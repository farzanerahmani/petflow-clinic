package com.roochi.petflowclinic.mapper;

import com.roochi.petflowshared.mapper.BaseMapper;
import com.roochi.petflowclinic.dto.request.CreateClinicRequestDto;
import com.roochi.petflowclinic.dto.request.UpdateClinicRequestDto;
import com.roochi.petflowclinic.dto.response.ClinicDetailResponseDto;
import com.roochi.petflowclinic.dto.response.ClinicResponseDto;
import com.roochi.petflowclinic.entity.Clinic;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClinicMapper extends BaseMapper<Clinic, ClinicResponseDto> {

    Clinic toEntity(CreateClinicRequestDto requestDto);

    ClinicResponseDto toResponse(Clinic clinic);

    List<ClinicResponseDto> toResponse(List<Clinic> clinics);

    void update(@MappingTarget Clinic clinic, UpdateClinicRequestDto requestDto);

    ClinicDetailResponseDto toClinicDetailResponseDto(Clinic clinic);
}
