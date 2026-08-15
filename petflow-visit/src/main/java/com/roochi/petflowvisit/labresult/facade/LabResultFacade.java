package com.roochi.petflowvisit.labresult.facade;

import com.roochi.petflowvisit.dto.request.labresult.*;
import com.roochi.petflowvisit.dto.response.labresult.*;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
public interface LabResultFacade {

    AddLabResultResponseDto addLabResult(AddLabResultRequestDto requestDto);

    UpdateLabResultResponseDto updateLabResult(UpdateLabResultRequestDto requestDto);

    DeleteLabResultResponseDto deleteLabResult(DeleteLabResultRequestDto requestDto);

    LabResultResponseDto getLabResultByLabRequestId(GetLabResultByLabRequestIdRequestDto requestDto);

    GetLabResultForUpdateResponseDto getLabResultForUpdate(GetLabResultForUpdateRequestDto requestDto);
}
