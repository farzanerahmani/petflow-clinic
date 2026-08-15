package com.roochi.petflowvisit.imaging.service.query;

import com.roochi.petflowvisit.dto.request.imaging.*;
import com.roochi.petflowvisit.dto.response.imaging.ImagingRequestResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.ImagingServiceResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.SearchImagingRequestResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.SearchImagingServiceResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
public interface ImagingRequestQueryService {
    ImagingRequestResponseDto getImagingRequestById(GetImagingRequestByIdRequestDto requestDto);

    ImagingRequestResponseDto getImagingRequestForUpdate(GetImagingRequestForUpdateRequestDto requestDto);

    SearchImagingRequestResponseDto searchImagingRequest(SearchImagingRequestRequestDto request);
}
