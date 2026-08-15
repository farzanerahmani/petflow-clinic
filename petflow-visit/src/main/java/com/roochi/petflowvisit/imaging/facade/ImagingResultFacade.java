package com.roochi.petflowvisit.imaging.facade;

import com.roochi.petflowvisit.dto.request.imaging.*;
import com.roochi.petflowvisit.dto.response.imaging.*;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
public interface ImagingResultFacade {

    AddImagingResultResponseDto addImagingResult(AddImagingResultRequestDto requestDto);

    UpdateImagingResultResponseDto updateImagingResult(UpdateImagingResultRequestDto requestDto);

    DeleteImagingResultResponseDto deleteImagingResult(DeleteImagingResultRequestDto requestDto);

    ImagingResultResponseDto getIImagingResultById(GetImagingResultByIdRequestDto requestDto);

    ImagingResultResponseDto getImagingResultForUpdate(GetImagingResultForUpdateRequestDto requestDto);


}
