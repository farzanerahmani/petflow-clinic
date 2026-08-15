package com.roochi.petflowvisit.hospitalization.facade;

import com.roochi.petflowvisit.dto.request.hospitalization.*;
import com.roochi.petflowvisit.dto.response.hospitalization.*;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
public interface HospitalizationFacade {

    HospitalizationResponseDto getHospitalizationById(GetHospitalizationByIdRequestDto responseDto);

    AddHospitalizationResponseDto addHospitalization(AddHospitalizationRequestDto requestDto);

    UpdateHospitalizationResponseDto updateHospitalization(UpdateHospitalizationRequestDto requestDto);

    DeleteHospitalizationResponseDto deleteHospitalization(DeleteHospitalizationRequestDto requestDto);

    HospitalizationResponseDto getHospitalizationForUpdate(GetHospitalizationForUpdateRequestDto requestDto);

    SearchHospitalizationResponseDto searchHospitalization(SearchHospitalizationRequestDto requestDto);

}

