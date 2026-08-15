package com.roochi.petflowvisit.imaging.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.cmmon.ImagingServiceDto;
import com.roochi.petflowvisit.dto.request.imaging.GetImagingServiceByIdRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.GetImagingServiceForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.SearchImagingServiceRequestDto;
import com.roochi.petflowvisit.dto.response.imaging.ImagingServiceResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.SearchImagingServiceResponseDto;
import com.roochi.petflowvisit.imaging.entity.ImagingService;
import com.roochi.petflowvisit.imaging.mapper.ImagingServiceMapper;
import com.roochi.petflowvisit.imaging.repository.ImagingServiceRepository;
import com.roochi.petflowvisit.imaging.service.query.ImagingServiceQueryService;
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
public class ImagingServiceQueryServiceImpl implements ImagingServiceQueryService {

    private final ImagingServiceRepository imagingServiceRepository;
    private final ImagingServiceMapper imagingServiceMapper;

    @Override
    public ImagingServiceResponseDto getImagingServiceById(GetImagingServiceByIdRequestDto requestDto) {
        ImagingService imagingService = imagingServiceRepository.findById(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        return imagingServiceMapper.toResponseDto(imagingService);
    }

    @Override
    public ImagingServiceResponseDto getImagingServiceForUpdate(GetImagingServiceForUpdateRequestDto requestDto) {
        ImagingService imagingService = imagingServiceRepository.findById(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        return imagingServiceMapper.toResponseDto(imagingService);
    }

    @Override
    public SearchImagingServiceResponseDto searchImagingService(SearchImagingServiceRequestDto requestDto) {

        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<ImagingService> page =
                imagingServiceRepository.search(
                        requestDto.getCode(),
                        requestDto.getName(),
                        requestDto.getActive(), pageRequest);

        List<ImagingServiceDto> imagingServices =
                page.getContent()
                        .stream()
                        .map(imagingServiceMapper::toImagingServiceDto).toList();

        SearchImagingServiceResponseDto response = new SearchImagingServiceResponseDto();
        response.setResults(imagingServices);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;


    }
}
