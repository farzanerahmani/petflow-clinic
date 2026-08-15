package com.roochi.petflowvisit.imaging.service.command;

import com.roochi.petflowvisit.dto.request.imaging.*;
import com.roochi.petflowvisit.dto.response.imaging.*;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
public interface ImagingServiceCommandService {

    AddImagingServiceResponseDto addImagingService(AddImagingServiceRequestDto requestDto);

    UpdateImagingServiceResponseDto updateImagingService(UpdateImagingServiceRequestDto requestDto);

    DeleteImagingServiceResponseDto deleteImagingService(DeleteImagingServiceRequestDto requestDto);
}
