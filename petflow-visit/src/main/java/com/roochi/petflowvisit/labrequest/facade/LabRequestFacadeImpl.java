package com.roochi.petflowvisit.labrequest.facade;

import com.roochi.petflowvisit.dto.request.labrequest.*;
import com.roochi.petflowvisit.dto.request.vaccination.*;
import com.roochi.petflowvisit.dto.response.labrequest.*;
import com.roochi.petflowvisit.dto.response.vaccination.*;
import com.roochi.petflowvisit.labrequest.service.command.LabRequestCommandService;
import com.roochi.petflowvisit.labrequest.service.query.LabRequestQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
@Component
@RequiredArgsConstructor
public class LabRequestFacadeImpl implements LabRequestFacade {

    private final LabRequestQueryService labRequestQueryService;
    private final LabRequestCommandService labRequestCommandService;


    @Override
    public AddLabRequestResponseDto addLabRequest(AddLabRequestRequestDto request) {
        return labRequestCommandService.addLabRequest(request);
    }

    @Override
    public UpdateLabRequestResponseDto updateLabRequest(UpdateLabRequestRequestDto request) {
        return labRequestCommandService.updateLabRequest(request);
    }

    @Override
    public DeleteLabRequestResponseDto deleteLabRequest(DeleteLabRequestRequestDto request) {
        return labRequestCommandService.deleteLabRequest(request);
    }

    @Override
    public GetAllLabRequestByVisitIdResponseDto getLabRequestByVisitId(GetAllLabRequestByVisitIdRequestDto request) {
        return labRequestQueryService.getLabRequestByVisitId(request);
    }

    @Override
    public GetLabRequestForUpdateResponseDto getLabRequestForUpdate(GetLabRequestForUpdateRequestDto request) {
        return labRequestQueryService.getLabRequestForUpdate(request);
    }
}
