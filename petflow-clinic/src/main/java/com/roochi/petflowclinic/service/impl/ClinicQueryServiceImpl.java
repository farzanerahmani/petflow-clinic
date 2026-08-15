package com.roochi.petflowclinic.service.impl;

import com.roochi.petflowclinic.dto.request.FindAllClinicsRequestDto;
import com.roochi.petflowclinic.dto.request.FindClinicByCodeRequestDto;
import com.roochi.petflowclinic.dto.request.FindClinicByIdRequestDto;
import com.roochi.petflowclinic.dto.response.ClinicResponseDto;
import com.roochi.petflowclinic.finder.ClinicFinder;
import com.roochi.petflowclinic.mapper.ClinicMapper;
import com.roochi.petflowclinic.repository.ClinicRepository;
import com.roochi.petflowclinic.service.query.ClinicQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClinicQueryServiceImpl implements ClinicQueryService {

    private final ClinicRepository clinicRepository;
    private final ClinicFinder clinicFinder;
    private final ClinicMapper clinicMapper;

    @Override
    public ClinicResponseDto findById(FindClinicByIdRequestDto requestDto) {
        return clinicMapper.toResponse(clinicFinder.findById(requestDto.getId()));
    }

    @Override
    public ClinicResponseDto findByCode(FindClinicByCodeRequestDto requestDto) {
        return clinicMapper.toResponse(clinicFinder.findByCode(requestDto.getCode()));
    }

    @Override
    public List<ClinicResponseDto> findAll(FindAllClinicsRequestDto requestDto) {
        return clinicMapper.toResponse(clinicRepository.findAll());
    }
}
