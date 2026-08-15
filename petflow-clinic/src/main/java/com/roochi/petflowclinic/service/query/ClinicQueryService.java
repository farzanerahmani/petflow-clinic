package com.roochi.petflowclinic.service.query;

import com.roochi.petflowshared.service.BaseQueryService;
import com.roochi.petflowclinic.dto.request.FindAllClinicsRequestDto;
import com.roochi.petflowclinic.dto.request.FindClinicByCodeRequestDto;
import com.roochi.petflowclinic.dto.request.FindClinicByIdRequestDto;
import com.roochi.petflowclinic.dto.response.ClinicResponseDto;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
public interface ClinicQueryService extends BaseQueryService<FindClinicByIdRequestDto, FindClinicByCodeRequestDto, FindAllClinicsRequestDto, ClinicResponseDto> {
    ClinicResponseDto findById(FindClinicByIdRequestDto requestDto);

    ClinicResponseDto findByCode(FindClinicByCodeRequestDto code);

    List<ClinicResponseDto> findAll(FindAllClinicsRequestDto requestDto);
}
