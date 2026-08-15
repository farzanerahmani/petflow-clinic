package com.roochi.petflowvisit.labtest.service.command;

import com.roochi.petflowvisit.dto.request.labtest.AddLabTestRequestDto;
import com.roochi.petflowvisit.dto.request.labtest.DeleteLabTestRequestDto;
import com.roochi.petflowvisit.dto.request.labtest.UpdateLabTestRequestDto;
import com.roochi.petflowvisit.dto.response.labtest.AddLabTestResponseDto;
import com.roochi.petflowvisit.dto.response.labtest.DeleteLabTestResponseDto;
import com.roochi.petflowvisit.dto.response.labtest.UpdateLabTestResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
public interface LabTestCommandService {
    AddLabTestResponseDto addLabTest(AddLabTestRequestDto requestDto);

    UpdateLabTestResponseDto updateLabTest(UpdateLabTestRequestDto requestDto);

    DeleteLabTestResponseDto deleteLabTest(DeleteLabTestRequestDto requestDto);
}
