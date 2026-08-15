package com.roochi.petflowvisit.procedure.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.cmmon.VisitProcedureDto;
import com.roochi.petflowvisit.dto.request.procedure.GetVisitProcedureByIdRequestDto;
import com.roochi.petflowvisit.dto.request.procedure.GetVisitProcedureForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.procedure.SearchVisitProcedureRequestDto;
import com.roochi.petflowvisit.dto.response.procedure.SearchVisitProcedureResponseDto;
import com.roochi.petflowvisit.dto.response.procedure.VisitProcedureResponseDto;
import com.roochi.petflowvisit.procedure.entity.VisitProcedure;
import com.roochi.petflowvisit.procedure.mapper.VisitProcedureMapper;
import com.roochi.petflowvisit.procedure.repository.VisitProcedureRepository;
import com.roochi.petflowvisit.procedure.service.query.VisitProcedureQueryService;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class VisitProcedureQueryServiceImpl implements VisitProcedureQueryService {
    private final VisitProcedureRepository visitProcedureRepository;
    private final VisitProcedureMapper visitProcedureMapper;

    @Override
    public VisitProcedureResponseDto getVisitProcedureById(GetVisitProcedureByIdRequestDto requestDto) {
        VisitProcedure entity = visitProcedureRepository.findById(requestDto.getId())
                .orElseThrow(() -> new
                        NotFoundException(ErrorCode.VALIDATION_ERROR));
        return visitProcedureMapper.toResponseDto(entity);
    }

    @Override
    public VisitProcedureResponseDto getVisitProcedureForUpdate(GetVisitProcedureForUpdateRequestDto requestDto) {
        VisitProcedure entity = visitProcedureRepository.findById(requestDto.getId())
                .orElseThrow(() -> new
                        NotFoundException(ErrorCode.VALIDATION_ERROR));
        return visitProcedureMapper.toResponseDto(entity);
    }

    @Override
    public SearchVisitProcedureResponseDto searchVisitProcedure(SearchVisitProcedureRequestDto requestDto) {

        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<VisitProcedure> page =
                visitProcedureRepository.search(
                        requestDto.getVisitId(),
                        requestDto.getProcedureId(),
                        requestDto.getPerformedById(),
                        requestDto.getFromDate(),
                        requestDto.getToDate()
                        , pageRequest);


        List<VisitProcedureDto> visitrPocedures =
                page.getContent()
                        .stream()
                        .map(visitProcedureMapper::toVisitProcedureDto).toList();

        SearchVisitProcedureResponseDto response = new SearchVisitProcedureResponseDto();
        response.setResults(visitrPocedures);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;

    }
}
