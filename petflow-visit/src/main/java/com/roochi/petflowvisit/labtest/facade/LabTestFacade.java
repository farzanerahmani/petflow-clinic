package com.roochi.petflowvisit.labtest.facade;

import com.roochi.petflowvisit.dto.request.labtest.*;
import com.roochi.petflowvisit.dto.response.labtest.*;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
public interface LabTestFacade {

    AddLabTestResponseDto addLabTest(AddLabTestRequestDto requestDto);

    UpdateLabTestResponseDto updateLabTest(UpdateLabTestRequestDto requestDto);

    DeleteLabTestResponseDto deleteLabTest(DeleteLabTestRequestDto requestDto);

    GetLabTestByIdResponseDto getLabTestById(GetLabTestByIdRequestDto requestDto);

    GetLabTestForUpdateResponseDto getLabTestForUpdate(GetLabTestForUpdateRequestDto requestDto);

    SearchLabTestResponseDto searchLabTest(SearchLabTestRequestDto requestDto);
}
