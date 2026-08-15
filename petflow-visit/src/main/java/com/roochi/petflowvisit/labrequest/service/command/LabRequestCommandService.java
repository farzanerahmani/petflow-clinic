package com.roochi.petflowvisit.labrequest.service.command;

import com.roochi.petflowvisit.dto.request.labrequest.AddLabRequestRequestDto;
import com.roochi.petflowvisit.dto.request.labrequest.DeleteLabRequestRequestDto;
import com.roochi.petflowvisit.dto.request.labrequest.UpdateLabRequestRequestDto;
import com.roochi.petflowvisit.dto.response.labrequest.AddLabRequestResponseDto;
import com.roochi.petflowvisit.dto.response.labrequest.DeleteLabRequestResponseDto;
import com.roochi.petflowvisit.dto.response.labrequest.UpdateLabRequestResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
public interface LabRequestCommandService {

    AddLabRequestResponseDto addLabRequest(AddLabRequestRequestDto request);

    UpdateLabRequestResponseDto updateLabRequest(UpdateLabRequestRequestDto request);

    DeleteLabRequestResponseDto deleteLabRequest(DeleteLabRequestRequestDto request);
}
