package com.roochi.petflowvisit.labrequest.facade;

import com.roochi.petflowvisit.dto.request.labrequest.*;
import com.roochi.petflowvisit.dto.response.labrequest.*;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
public interface LabRequestFacade {
    AddLabRequestResponseDto addLabRequest(AddLabRequestRequestDto request);

    UpdateLabRequestResponseDto updateLabRequest(UpdateLabRequestRequestDto request);

    DeleteLabRequestResponseDto deleteLabRequest(DeleteLabRequestRequestDto request);

    GetAllLabRequestByVisitIdResponseDto getLabRequestByVisitId(GetAllLabRequestByVisitIdRequestDto request);

    GetLabRequestForUpdateResponseDto getLabRequestForUpdate(GetLabRequestForUpdateRequestDto request);
}
