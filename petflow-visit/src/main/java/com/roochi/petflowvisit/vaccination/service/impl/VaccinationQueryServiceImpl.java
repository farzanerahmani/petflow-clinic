package com.roochi.petflowvisit.vaccination.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.cmmon.VaccinationDto;
import com.roochi.petflowvisit.dto.request.vaccination.GetAllVaccinationByVisitIdRequestDto;
import com.roochi.petflowvisit.dto.request.vaccination.GetVaccinationForUpdateRequestDto;
import com.roochi.petflowvisit.dto.response.vaccination.GetAllVaccinationByVisitIdResponseDto;
import com.roochi.petflowvisit.dto.response.vaccination.GetVaccinationForUpdateResponseDto;
import com.roochi.petflowvisit.vaccination.entity.Vaccination;
import com.roochi.petflowvisit.vaccination.mapper.VaccinationMapper;
import com.roochi.petflowvisit.vaccination.repository.VaccinationRepository;
import com.roochi.petflowvisit.vaccination.service.query.VaccinationQueryService;
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
public class VaccinationQueryServiceImpl implements VaccinationQueryService {

    private final VaccinationRepository vaccinationRepository;
    private final VaccinationMapper vaccinationMapper;

    @Override
    public GetAllVaccinationByVisitIdResponseDto getVaccinationByVisitId(GetAllVaccinationByVisitIdRequestDto request) {

        Pageable pageRequest = PageRequest.of(request.getPageNumber(),
                request.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<Vaccination> page = vaccinationRepository.findByVisitId(
                request.getVisitId(), pageRequest);
        List<VaccinationDto> vaccinations =
                page.getContent()
                        .stream()
                        .map(vaccinationMapper::toVaccinationDto).toList();

        GetAllVaccinationByVisitIdResponseDto response = new GetAllVaccinationByVisitIdResponseDto();
        response.setResults(vaccinations);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }

    @Override
    public GetVaccinationForUpdateResponseDto getVaccinationForUpdate(GetVaccinationForUpdateRequestDto request) {
        Vaccination vaccination = vaccinationRepository.findByIdForUpdate(request.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        return GetVaccinationForUpdateResponseDto.builder()
                .vaccination(vaccinationMapper.toVaccinationDto(vaccination))
                .build();
    }


}
