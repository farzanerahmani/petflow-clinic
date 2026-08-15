package com.roochi.petflowvisit.imaging.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.cmmon.ImagingRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.GetImagingRequestByIdRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.GetImagingRequestForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.SearchImagingRequestRequestDto;
import com.roochi.petflowvisit.dto.response.imaging.ImagingRequestResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.SearchImagingRequestResponseDto;
import com.roochi.petflowvisit.imaging.entity.ImagingRequest;
import com.roochi.petflowvisit.imaging.mapper.ImagingRequestMapper;
import com.roochi.petflowvisit.imaging.repository.ImagingRequestRepository;
import com.roochi.petflowvisit.imaging.service.query.ImagingRequestQueryService;
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
 * @created 7/22/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImagingRequestQueryServiceImpl implements ImagingRequestQueryService {
    private final ImagingRequestRepository imagingRequestRepository;
    private final ImagingRequestMapper imagingRequestMapper;

    @Override
    public ImagingRequestResponseDto getImagingRequestById(GetImagingRequestByIdRequestDto requestDto) {
        ImagingRequest imagingRequest = imagingRequestRepository.findById(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        return imagingRequestMapper.toResponseDto(imagingRequest);
    }

    @Override
    public ImagingRequestResponseDto getImagingRequestForUpdate(GetImagingRequestForUpdateRequestDto requestDto) {
        ImagingRequest imagingRequest = imagingRequestRepository.findById(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        return imagingRequestMapper.toResponseDto(imagingRequest);
    }

    @Override
    public SearchImagingRequestResponseDto searchImagingRequest(SearchImagingRequestRequestDto requestDto) {
        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<ImagingRequest> page =
                imagingRequestRepository.search(
                        requestDto.getVisitId(),
                        requestDto.getImagingServiceId(),
                        requestDto.getFromDate(),
                        requestDto.getToDate(), pageRequest);


        List<ImagingRequestDto> imagingRequests =
                page.getContent()
                        .stream()
                        .map(imagingRequestMapper::toImagingRequestDto).toList();

        SearchImagingRequestResponseDto response = new SearchImagingRequestResponseDto();
        response.setResults(imagingRequests);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;

    }
}
