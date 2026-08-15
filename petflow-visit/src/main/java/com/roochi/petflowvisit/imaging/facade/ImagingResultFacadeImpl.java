package com.roochi.petflowvisit.imaging.facade;

import com.roochi.petflowvisit.dto.request.imaging.*;
import com.roochi.petflowvisit.dto.response.imaging.AddImagingResultResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.DeleteImagingResultResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.ImagingResultResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.UpdateImagingResultResponseDto;
import com.roochi.petflowvisit.imaging.service.command.ImagingResultCommandService;
import com.roochi.petflowvisit.imaging.service.query.ImagingResultQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/23/2026
 */
@Component
@RequiredArgsConstructor
public class ImagingResultFacadeImpl implements ImagingResultFacade{

    private final ImagingResultCommandService imagingResultCommandService;
    private final ImagingResultQueryService imagingResultQueryService;

    @Override
    public AddImagingResultResponseDto addImagingResult(AddImagingResultRequestDto requestDto) {
        return imagingResultCommandService.addImagingResult(requestDto);
    }

    @Override
    public UpdateImagingResultResponseDto updateImagingResult(UpdateImagingResultRequestDto requestDto) {
        return imagingResultCommandService.updateImagingResult(requestDto);
    }

    @Override
    public DeleteImagingResultResponseDto deleteImagingResult(DeleteImagingResultRequestDto requestDto) {
        return imagingResultCommandService.deleteImagingResult(requestDto);
    }

    @Override
    public ImagingResultResponseDto getIImagingResultById(GetImagingResultByIdRequestDto requestDto) {
        return imagingResultQueryService.getImagingResultById(requestDto);
    }

    @Override
    public ImagingResultResponseDto getImagingResultForUpdate(GetImagingResultForUpdateRequestDto requestDto) {
        return imagingResultQueryService.getImagingResultForUpdate(requestDto);
    }
}
