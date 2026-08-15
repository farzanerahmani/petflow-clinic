package com.roochi.petflowvisit.imaging.service.command;

import com.roochi.petflowvisit.dto.request.imaging.*;
import com.roochi.petflowvisit.dto.response.imaging.*;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
public interface ImagingResultCommandService {

    AddImagingResultResponseDto addImagingResult(AddImagingResultRequestDto requestDto);

    UpdateImagingResultResponseDto updateImagingResult(UpdateImagingResultRequestDto requestDto);

    DeleteImagingResultResponseDto deleteImagingResult(DeleteImagingResultRequestDto requestDto);
}
