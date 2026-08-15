package com.roochi.petflowvisit.labtest.service.query;

import com.roochi.petflowvisit.dto.request.labtest.GetLabTestByIdRequestDto;
import com.roochi.petflowvisit.dto.request.labtest.GetLabTestForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.labtest.SearchLabTestRequestDto;
import com.roochi.petflowvisit.dto.response.labtest.GetLabTestByIdResponseDto;
import com.roochi.petflowvisit.dto.response.labtest.GetLabTestForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.labtest.SearchLabTestResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
public interface LabTestQueryService {
    GetLabTestByIdResponseDto getLabTestById(GetLabTestByIdRequestDto requestDto);

    GetLabTestForUpdateResponseDto getLabTestForUpdate(GetLabTestForUpdateRequestDto requestDto);

    SearchLabTestResponseDto searchLabTest(SearchLabTestRequestDto requestDto);
}
