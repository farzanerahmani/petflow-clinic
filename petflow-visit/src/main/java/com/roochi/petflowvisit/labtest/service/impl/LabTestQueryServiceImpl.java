package com.roochi.petflowvisit.labtest.service.impl;

import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.drug.specification.DrugSpecification;
import com.roochi.petflowvisit.dto.cmmon.DrugDto;
import com.roochi.petflowvisit.dto.cmmon.LabTestDto;
import com.roochi.petflowvisit.dto.request.drug.GetDrugByIdRequestDto;
import com.roochi.petflowvisit.dto.request.drug.GetDrugForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.drug.SearchDrugRequestDto;
import com.roochi.petflowvisit.dto.request.labtest.GetLabTestByIdRequestDto;
import com.roochi.petflowvisit.dto.request.labtest.GetLabTestForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.labtest.SearchLabTestRequestDto;
import com.roochi.petflowvisit.dto.response.drug.GetDrugByIdResponseDto;
import com.roochi.petflowvisit.dto.response.drug.GetDrugForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.drug.SearchDrugResponseDto;
import com.roochi.petflowvisit.dto.response.labtest.GetLabTestByIdResponseDto;
import com.roochi.petflowvisit.dto.response.labtest.GetLabTestForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.labtest.SearchLabTestResponseDto;
import com.roochi.petflowvisit.labtest.entity.LabTest;
import com.roochi.petflowvisit.labtest.mapper.LabTestMapper;
import com.roochi.petflowvisit.labtest.repository.LabTestRepository;
import com.roochi.petflowvisit.labtest.service.query.LabTestQueryService;
import com.roochi.petflowvisit.labtest.specification.LabTestSpecification;
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
public class LabTestQueryServiceImpl implements LabTestQueryService {

    private final LabTestRepository labTestRepository;

    private final LabTestMapper labTestMapper;


    @Override
    public GetLabTestByIdResponseDto getLabTestById(GetLabTestByIdRequestDto requestDto) {
        LabTest labTest = labTestRepository.findById(requestDto.getId())
                .orElseThrow();
        return GetLabTestByIdResponseDto.builder()
                .labTest(labTestMapper.toLabTestDto(labTest))
                .build();
    }

    @Override
    public GetLabTestForUpdateResponseDto getLabTestForUpdate(GetLabTestForUpdateRequestDto requestDto) {
        LabTest labTest = labTestRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow();
        return GetLabTestForUpdateResponseDto.builder()
                .labTest(labTestMapper.toLabTestDto(labTest))
                .build();
    }

    @Override
    public SearchLabTestResponseDto searchLabTest(SearchLabTestRequestDto requestDto) {
        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<LabTest> page = labTestRepository.findAll(
                LabTestSpecification.filter(requestDto), pageRequest);
        List<LabTestDto> labTests =
                page.getContent()
                        .stream()
                        .map(labTestMapper::toLabTestDto).toList();

        SearchLabTestResponseDto response = new SearchLabTestResponseDto();
        response.setResults(labTests);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
