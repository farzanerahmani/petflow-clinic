package com.roochi.petflowvisit.labrequest.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.cmmon.LabRequestDto;
import com.roochi.petflowvisit.dto.cmmon.VaccinationDto;
import com.roochi.petflowvisit.dto.request.labrequest.GetAllLabRequestByVisitIdRequestDto;
import com.roochi.petflowvisit.dto.request.labrequest.GetLabRequestForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.vaccination.GetAllVaccinationByVisitIdRequestDto;
import com.roochi.petflowvisit.dto.request.vaccination.GetVaccinationForUpdateRequestDto;
import com.roochi.petflowvisit.dto.response.labrequest.GetAllLabRequestByVisitIdResponseDto;
import com.roochi.petflowvisit.dto.response.labrequest.GetLabRequestForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.vaccination.GetAllVaccinationByVisitIdResponseDto;
import com.roochi.petflowvisit.dto.response.vaccination.GetVaccinationForUpdateResponseDto;
import com.roochi.petflowvisit.labrequest.entity.LabRequest;
import com.roochi.petflowvisit.labrequest.mapper.LabRequestMapper;
import com.roochi.petflowvisit.labrequest.repository.LabRequestRepository;
import com.roochi.petflowvisit.labrequest.service.query.LabRequestQueryService;
import com.roochi.petflowvisit.vaccination.entity.Vaccination;
import com.roochi.petflowvisit.vaccination.mapper.VaccinationMapper;
import com.roochi.petflowvisit.vaccination.repository.VaccinationRepository;
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
 * @created 7/20/2026
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LabRequestQueryServiceImpl implements LabRequestQueryService {

    private final LabRequestRepository labRequestRepository;
    private final LabRequestMapper labRequestMapper;

    @Override
    public GetAllLabRequestByVisitIdResponseDto getLabRequestByVisitId(GetAllLabRequestByVisitIdRequestDto request) {

        Pageable pageRequest = PageRequest.of(request.getPageNumber(),
                request.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<LabRequest> page = labRequestRepository.findByVisitId(
                request.getVisitId(), pageRequest);
        List<LabRequestDto> labRequests =
                page.getContent()
                        .stream()
                        .map(labRequestMapper::toLabRequestDto).toList();

        GetAllLabRequestByVisitIdResponseDto response = new GetAllLabRequestByVisitIdResponseDto();
        response.setResults(labRequests);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }

    @Override
    public GetLabRequestForUpdateResponseDto getLabRequestForUpdate(GetLabRequestForUpdateRequestDto request) {
        LabRequest labRequest = labRequestRepository.findByIdForUpdate(request.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        return GetLabRequestForUpdateResponseDto.builder()
                .labRequest(labRequestMapper.toLabRequestDto(labRequest))
                .build();
    }


}
