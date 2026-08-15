package com.roochi.petflowvisit.imaging.facade;

import com.roochi.petflowvisit.dto.request.imaging.*;
import com.roochi.petflowvisit.dto.response.imaging.*;
import com.roochi.petflowvisit.imaging.service.command.ImagingServiceCommandService;
import com.roochi.petflowvisit.imaging.service.query.ImagingServiceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Component
@RequiredArgsConstructor
public class ImagingServiceFacadeImpl implements ImagingServiceFacade{

    private final ImagingServiceCommandService imagingServiceCommandService;
    private final ImagingServiceQueryService imagingServiceQueryService;

    @Override
    public AddImagingServiceResponseDto addImagingService(AddImagingServiceRequestDto requestDto) {
        return imagingServiceCommandService.addImagingService(requestDto);
    }

    @Override
    public UpdateImagingServiceResponseDto updateImagingService(UpdateImagingServiceRequestDto requestDto) {
        return imagingServiceCommandService.updateImagingService(requestDto);
    }

    @Override
    public DeleteImagingServiceResponseDto deleteImagingService(DeleteImagingServiceRequestDto requestDto) {
        return imagingServiceCommandService.deleteImagingService(requestDto);
    }

    @Override
    public ImagingServiceResponseDto getImagingServiceById(GetImagingServiceByIdRequestDto requestDto) {
        return imagingServiceQueryService.getImagingServiceById(requestDto);
    }

    @Override
    public ImagingServiceResponseDto getImagingServiceForUpdate(GetImagingServiceForUpdateRequestDto requestDto) {
        return imagingServiceQueryService.getImagingServiceForUpdate(requestDto);
    }

    @Override
    public SearchImagingServiceResponseDto searchImagingService(SearchImagingServiceRequestDto requestDto) {
        return imagingServiceQueryService.searchImagingService(requestDto);
    }
}
