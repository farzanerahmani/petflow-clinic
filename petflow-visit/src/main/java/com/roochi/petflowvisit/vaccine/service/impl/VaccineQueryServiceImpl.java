package com.roochi.petflowvisit.vaccine.service.impl;

import com.roochi.petflowvisit.dto.cmmon.VaccineDto;
import com.roochi.petflowvisit.dto.request.vaccine.GetVaccineByIdRequestDto;
import com.roochi.petflowvisit.dto.request.vaccine.GetVaccineForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.vaccine.SearchVaccineRequestDto;
import com.roochi.petflowvisit.dto.response.vaccine.GetVaccineByIdResponseDto;
import com.roochi.petflowvisit.dto.response.vaccine.GetVaccineForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.vaccine.SearchVaccineResponseDto;
import com.roochi.petflowvisit.vaccine.entity.Vaccine;
import com.roochi.petflowvisit.vaccine.mapper.VaccineMapper;
import com.roochi.petflowvisit.vaccine.repository.VaccineRepository;
import com.roochi.petflowvisit.vaccine.service.query.VaccineQueryService;
import com.roochi.petflowvisit.vaccine.specification.VaccineSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VaccineQueryServiceImpl implements VaccineQueryService {

    private final VaccineRepository vaccineRepository;

    private final VaccineMapper vaccineMapper;

    @Override
    public GetVaccineByIdResponseDto getVaccineById(GetVaccineByIdRequestDto requestDto) {

        Vaccine vaccine = vaccineRepository.findById(requestDto.getVaccineId())
                .orElseThrow();
        return GetVaccineByIdResponseDto.builder()
                .vaccine(vaccineMapper.toVaccineDto(vaccine))
                .build();
    }

    @Override
    public GetVaccineForUpdateResponseDto getVaccineForUpdate(GetVaccineForUpdateRequestDto requestDto) {
        Vaccine vaccine = vaccineRepository.findByIdForUpdate(requestDto.getVaccineId())
                .orElseThrow();
        return GetVaccineForUpdateResponseDto.builder()
                .vaccine(vaccineMapper.toVaccineDto(vaccine))
                .build();
    }

    @Override
    public SearchVaccineResponseDto searchVaccine(SearchVaccineRequestDto requestDto) {
        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<Vaccine> page = vaccineRepository.findAll(
                VaccineSpecification.filter(requestDto), pageRequest);

        List<VaccineDto> vaccines =
                page.getContent()
                        .stream()
                        .map(vaccineMapper::toVaccineDto).toList();

        SearchVaccineResponseDto response = new SearchVaccineResponseDto();
        response.setResults(vaccines);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
