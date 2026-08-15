package com.roochi.petflowvisit.labresult.service.command;

import com.roochi.petflowvisit.dto.request.labresult.AddLabResultRequestDto;
import com.roochi.petflowvisit.dto.request.labresult.DeleteLabResultRequestDto;
import com.roochi.petflowvisit.dto.request.labresult.UpdateLabResultRequestDto;
import com.roochi.petflowvisit.dto.response.labresult.AddLabResultResponseDto;
import com.roochi.petflowvisit.dto.response.labresult.DeleteLabResultResponseDto;
import com.roochi.petflowvisit.dto.response.labresult.UpdateLabResultResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
public interface LabResultCommandService {

    AddLabResultResponseDto addLabResultService(AddLabResultRequestDto requestDto);

    UpdateLabResultResponseDto updateLabResultService(UpdateLabResultRequestDto requestDto);

    DeleteLabResultResponseDto deleteLabResultService(DeleteLabResultRequestDto requestDto);
}
