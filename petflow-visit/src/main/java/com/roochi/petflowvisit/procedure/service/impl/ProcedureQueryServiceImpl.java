package com.roochi.petflowvisit.procedure.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.cmmon.ProcedureDto;
import com.roochi.petflowvisit.dto.request.procedure.GetProcedureByIdRequestDto;
import com.roochi.petflowvisit.dto.request.procedure.GetProcedureForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.procedure.SearchProcedureRequestDto;
import com.roochi.petflowvisit.dto.response.procedure.GetProcedureByIdResponseDto;
import com.roochi.petflowvisit.dto.response.procedure.GetProcedureForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.procedure.SearchProcedureResponseDto;
import com.roochi.petflowvisit.procedure.entity.Procedure;
import com.roochi.petflowvisit.procedure.mapper.ProcedureMapper;
import com.roochi.petflowvisit.procedure.repository.ProcedureRepository;
import com.roochi.petflowvisit.procedure.service.query.ProcedureQueryService;
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
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProcedureQueryServiceImpl implements ProcedureQueryService {

    private final ProcedureRepository procedureRepository;

    private final ProcedureMapper procedureMapper;

    @Override
    public GetProcedureByIdResponseDto getProcedureById(GetProcedureByIdRequestDto requestDto) {

        Procedure procedure = procedureRepository.findById(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        return GetProcedureByIdResponseDto.builder()
                .procedure(procedureMapper.toProcedureDto(procedure))
                .build();
    }

    @Override
    public GetProcedureForUpdateResponseDto getProcedureForUpdate(GetProcedureForUpdateRequestDto requestDto) {
        Procedure procedure = procedureRepository.findById(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        return GetProcedureForUpdateResponseDto.builder()
                .procedure(procedureMapper.toProcedureDto(procedure))
                .build();
    }

    @Override
    public SearchProcedureResponseDto searchProcedure(SearchProcedureRequestDto requestDto) {
        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<Procedure> page = procedureRepository.search(
                requestDto.getCode(),
                requestDto.getName(),
                requestDto.getActive(),
                pageRequest);

        List<ProcedureDto> procedures =
                page.getContent()
                        .stream()
                        .map(procedureMapper::toProcedureDto).toList();

        SearchProcedureResponseDto response = new SearchProcedureResponseDto();
        response.setResults(procedures);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
