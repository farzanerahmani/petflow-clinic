package com.roochi.petflowvisit.labresult.service.query;

import com.roochi.petflowvisit.dto.request.labresult.GetLabResultByLabRequestIdRequestDto;
import com.roochi.petflowvisit.dto.request.labresult.GetLabResultForUpdateRequestDto;
import com.roochi.petflowvisit.dto.response.labresult.GetLabResultForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.labresult.LabResultResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
public interface LabResultQueryService {

    LabResultResponseDto getLabResultByLabRequestId(GetLabResultByLabRequestIdRequestDto requestDto);

    GetLabResultForUpdateResponseDto getLabResultForUpdate(GetLabResultForUpdateRequestDto requestDto);
}
