package com.roochi.petflowvisit.labresult.facade;

import com.roochi.petflowvisit.dto.request.labresult.*;
import com.roochi.petflowvisit.dto.response.labresult.*;
import com.roochi.petflowvisit.labresult.service.command.LabResultCommandService;
import com.roochi.petflowvisit.labresult.service.query.LabResultQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Component
@RequiredArgsConstructor
public class LabResultFacadeImpl implements LabResultFacade{

    private final LabResultQueryService labResultQueryService;
    private final LabResultCommandService labResultCommandService;
    @Override
    public AddLabResultResponseDto addLabResult(AddLabResultRequestDto requestDto) {
        return labResultCommandService.addLabResultService(requestDto);
    }

    @Override
    public UpdateLabResultResponseDto updateLabResult(UpdateLabResultRequestDto requestDto) {
        return labResultCommandService.updateLabResultService(requestDto);
    }

    @Override
    public DeleteLabResultResponseDto deleteLabResult(DeleteLabResultRequestDto requestDto) {
        return labResultCommandService.deleteLabResultService(requestDto);
    }

    @Override
    public LabResultResponseDto getLabResultByLabRequestId(GetLabResultByLabRequestIdRequestDto requestDto) {
        return labResultQueryService.getLabResultByLabRequestId(requestDto);
    }

    @Override
    public GetLabResultForUpdateResponseDto getLabResultForUpdate(GetLabResultForUpdateRequestDto requestDto) {
        return labResultQueryService.getLabResultForUpdate(requestDto);
    }
}
