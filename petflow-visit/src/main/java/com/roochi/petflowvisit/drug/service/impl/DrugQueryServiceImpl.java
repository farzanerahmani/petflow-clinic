package com.roochi.petflowvisit.drug.service.impl;

import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.drug.mapper.DrugMapper;
import com.roochi.petflowvisit.drug.repository.DrugRepository;
import com.roochi.petflowvisit.drug.service.query.DrugQueryService;
import com.roochi.petflowvisit.drug.specification.DrugSpecification;
import com.roochi.petflowvisit.dto.cmmon.DrugDto;
import com.roochi.petflowvisit.dto.request.drug.GetDrugByIdRequestDto;
import com.roochi.petflowvisit.dto.request.drug.GetDrugForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.drug.SearchDrugRequestDto;
import com.roochi.petflowvisit.dto.response.drug.GetDrugByIdResponseDto;
import com.roochi.petflowvisit.dto.response.drug.GetDrugForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.drug.SearchDrugResponseDto;
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
 * @created 7/18/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DrugQueryServiceImpl implements DrugQueryService {

    private final DrugRepository drugRepository;

    private final DrugMapper drugMapper;


    @Override
    public GetDrugByIdResponseDto getDrugById(GetDrugByIdRequestDto requestDto) {
        Drug drug = drugRepository.findById(requestDto.getId())
                .orElseThrow();
        return GetDrugByIdResponseDto.builder()
                .drug(drugMapper.toDrugDto(drug))
                .build();
    }

    @Override
    public GetDrugForUpdateResponseDto getDrugForUpdate(GetDrugForUpdateRequestDto requestDto) {
        Drug drug = drugRepository.findByIdForUpdate(requestDto.getDrugId())
                .orElseThrow();
        return GetDrugForUpdateResponseDto.builder()
                .drug(drugMapper.toDrugDto(drug))
                .build();
    }

    @Override
    public SearchDrugResponseDto searchDrug(SearchDrugRequestDto requestDto) {
        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<Drug> page = drugRepository.findAll(
                DrugSpecification.filter(requestDto), pageRequest);
        List<DrugDto> drugs =
                page.getContent()
                        .stream()
                        .map(drugMapper::toDrugDto).toList();

        SearchDrugResponseDto response = new SearchDrugResponseDto();
        response.setResults(drugs);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
