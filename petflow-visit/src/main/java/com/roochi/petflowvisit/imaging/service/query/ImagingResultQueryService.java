package com.roochi.petflowvisit.imaging.service.query;

import com.roochi.petflowvisit.dto.request.imaging.GetImagingRequestByIdRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.GetImagingRequestForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.GetImagingResultByIdRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.GetImagingResultForUpdateRequestDto;
import com.roochi.petflowvisit.dto.response.imaging.ImagingRequestResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.ImagingResultResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
public interface ImagingResultQueryService {
    ImagingResultResponseDto getImagingResultById(GetImagingResultByIdRequestDto requestDto);

    ImagingResultResponseDto getImagingResultForUpdate(GetImagingResultForUpdateRequestDto requestDto);

}
