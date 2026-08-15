package com.roochi.petflowvisit.imaging.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.imaging.GetImagingResultByIdRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.GetImagingResultForUpdateRequestDto;
import com.roochi.petflowvisit.dto.response.imaging.ImagingResultResponseDto;
import com.roochi.petflowvisit.imaging.entity.ImagingResult;
import com.roochi.petflowvisit.imaging.mapper.ImagingResultMapper;
import com.roochi.petflowvisit.imaging.repository.ImagingResultRepository;
import com.roochi.petflowvisit.imaging.service.query.ImagingResultQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author farzane.rahmani
 * @created 7/23/2026
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImagingResultQueryServiceImpl implements ImagingResultQueryService {

    private final ImagingResultRepository imagingResultRepository;

    private final ImagingResultMapper imagingResultMapper;
    @Override
    public ImagingResultResponseDto getImagingResultById(GetImagingResultByIdRequestDto requestDto) {
        ImagingResult imagingResult = imagingResultRepository
                .findByImagingRequestId(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        return imagingResultMapper.toResponseDto(imagingResult);
    }

    @Override
    public ImagingResultResponseDto getImagingResultForUpdate(GetImagingResultForUpdateRequestDto requestDto) {
        ImagingResult imagingResult = imagingResultRepository
                .findById(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        return imagingResultMapper.toResponseDto(imagingResult);
    }
}
