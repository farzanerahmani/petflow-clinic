package com.roochi.petflowvisit.imaging.facade;

import com.roochi.petflowvisit.dto.request.drug.*;
import com.roochi.petflowvisit.dto.request.imaging.*;
import com.roochi.petflowvisit.dto.response.drug.*;
import com.roochi.petflowvisit.dto.response.imaging.*;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
public interface ImagingServiceFacade {

    AddImagingServiceResponseDto addImagingService(AddImagingServiceRequestDto requestDto);

    UpdateImagingServiceResponseDto updateImagingService(UpdateImagingServiceRequestDto requestDto);

    DeleteImagingServiceResponseDto deleteImagingService(DeleteImagingServiceRequestDto requestDto);

    ImagingServiceResponseDto getImagingServiceById(GetImagingServiceByIdRequestDto requestDto);

    ImagingServiceResponseDto getImagingServiceForUpdate(GetImagingServiceForUpdateRequestDto requestDto);

    SearchImagingServiceResponseDto searchImagingService(SearchImagingServiceRequestDto requestDto);

}
