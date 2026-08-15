package com.roochi.petflowvisit.hospitalization.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.cmmon.HospitalizationDto;
import com.roochi.petflowvisit.dto.request.hospitalization.GetHospitalizationForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.GetHospitalizationByIdRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.SearchHospitalizationRequestDto;
import com.roochi.petflowvisit.dto.response.hospitalization.HospitalizationResponseDto;
import com.roochi.petflowvisit.dto.response.hospitalization.SearchHospitalizationResponseDto;
import com.roochi.petflowvisit.hospitalization.entity.Hospitalization;
import com.roochi.petflowvisit.hospitalization.mapper.HospitalizationMapper;
import com.roochi.petflowvisit.hospitalization.repository.HospitalizationRepository;
import com.roochi.petflowvisit.hospitalization.service.query.HospitalizationQueryService;
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
 * @created 7/24/2026
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HospitalizationQueryServiceImpl implements HospitalizationQueryService {
    private final HospitalizationRepository hospitalizationRepository;
    private final HospitalizationMapper hospitalizationMapper;

    @Override
    public HospitalizationResponseDto getHospitalizationById(GetHospitalizationByIdRequestDto responseDto) {
        Hospitalization hospitalization = hospitalizationRepository.findById(responseDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));
        return hospitalizationMapper.toResponseDto(hospitalization);
    }

    @Override
    public HospitalizationResponseDto getHospitalizationForUpdate(GetHospitalizationForUpdateRequestDto requestDto) {
        Hospitalization hospitalization = hospitalizationRepository.findById(requestDto.getId())
                .orElseThrow(() -> new
                        NotFoundException(ErrorCode.VALIDATION_ERROR));
        return hospitalizationMapper.toResponseDto(hospitalization);
    }

    @Override
    public SearchHospitalizationResponseDto searchHospitalization(SearchHospitalizationRequestDto requestDto) {

        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<Hospitalization> page = hospitalizationRepository.search(
                requestDto.getVisitId(),
                requestDto.getStatus(),
                requestDto.getAttendingVeterinarianId(),
                requestDto.getAdmissionFrom(),
                requestDto.getAdmissionTo()
                , pageRequest);

        List<HospitalizationDto> hospitalizations =
                page.getContent()
                        .stream()
                        .map(hospitalizationMapper::toHospitalizationDto).toList();

        SearchHospitalizationResponseDto response = new SearchHospitalizationResponseDto();
        response.setResults(hospitalizations);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
