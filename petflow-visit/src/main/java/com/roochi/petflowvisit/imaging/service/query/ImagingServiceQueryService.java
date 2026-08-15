package com.roochi.petflowvisit.imaging.service.query;

import com.roochi.petflowvisit.dto.request.imaging.*;
import com.roochi.petflowvisit.dto.response.imaging.*;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
public interface ImagingServiceQueryService {
    ImagingServiceResponseDto getImagingServiceById(GetImagingServiceByIdRequestDto requestDto);

    ImagingServiceResponseDto getImagingServiceForUpdate(GetImagingServiceForUpdateRequestDto requestDto);

    SearchImagingServiceResponseDto searchImagingService(SearchImagingServiceRequestDto request);
}
