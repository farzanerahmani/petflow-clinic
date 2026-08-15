package com.roochi.petflowvisit.hospitalization.service.command;

import com.roochi.petflowvisit.dto.request.hospitalization.AddHospitalizationRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.DeleteHospitalizationRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.UpdateHospitalizationRequestDto;
import com.roochi.petflowvisit.dto.response.hospitalization.AddHospitalizationResponseDto;
import com.roochi.petflowvisit.dto.response.hospitalization.DeleteHospitalizationResponseDto;
import com.roochi.petflowvisit.dto.response.hospitalization.UpdateHospitalizationResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
public interface HospitalizationCommandService {

    AddHospitalizationResponseDto addHospitalization(AddHospitalizationRequestDto requestDto);

    UpdateHospitalizationResponseDto updateHospitalization(UpdateHospitalizationRequestDto requestDto);

    DeleteHospitalizationResponseDto deleteHospitalization(DeleteHospitalizationRequestDto requestDto);
}
