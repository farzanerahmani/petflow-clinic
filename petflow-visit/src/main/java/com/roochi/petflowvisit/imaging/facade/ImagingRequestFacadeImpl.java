package com.roochi.petflowvisit.imaging.facade;

import com.roochi.petflowvisit.dto.request.imaging.*;
import com.roochi.petflowvisit.dto.response.imaging.*;
import com.roochi.petflowvisit.imaging.service.command.ImagingRequestCommandService;
import com.roochi.petflowvisit.imaging.service.query.ImagingRequestQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Component
@RequiredArgsConstructor
public class ImagingRequestFacadeImpl implements ImagingRequestFacade {

    private final ImagingRequestCommandService imagingRequestCommandService;
    private final ImagingRequestQueryService imagingRequestQueryService;
    @Override
    public AddImagingRequestResponseDto addImagingRequest(AddImagingRequestRequestDto requestDto) {
        return imagingRequestCommandService.addImagingRequest(requestDto);
    }

    @Override
    public UpdateImagingRequestResponseDto updateImagingRequest(UpdateImagingRequestRequestDto requestDto) {
        return imagingRequestCommandService.updateImagingRequest(requestDto);
    }

    @Override
    public DeleteImagingRequestResponseDto deleteImagingRequest(DeleteImagingRequestRequestDto requestDto) {
        return imagingRequestCommandService.deleteImagingRequest(requestDto);
    }

    @Override
    public ImagingRequestResponseDto getIImagingRequestById(GetImagingRequestByIdRequestDto requestDto) {
        return imagingRequestQueryService.getImagingRequestById(requestDto);
    }

    @Override
    public ImagingRequestResponseDto getImagingRequestForUpdate(GetImagingRequestForUpdateRequestDto requestDto) {
        return imagingRequestQueryService.getImagingRequestForUpdate(requestDto);
    }

    @Override
    public SearchImagingRequestResponseDto searchImagingRequest(SearchImagingRequestRequestDto requestDto) {
        return imagingRequestQueryService.searchImagingRequest(requestDto);
    }
}
