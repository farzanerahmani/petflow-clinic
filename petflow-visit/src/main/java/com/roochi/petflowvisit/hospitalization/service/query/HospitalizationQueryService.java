package com.roochi.petflowvisit.hospitalization.service.query;

import com.roochi.petflowvisit.dto.request.hospitalization.GetHospitalizationForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.GetHospitalizationByIdRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.SearchHospitalizationRequestDto;
import com.roochi.petflowvisit.dto.response.hospitalization.HospitalizationResponseDto;
import com.roochi.petflowvisit.dto.response.hospitalization.SearchHospitalizationResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
public interface HospitalizationQueryService {

    HospitalizationResponseDto getHospitalizationById(GetHospitalizationByIdRequestDto responseDto);

    HospitalizationResponseDto getHospitalizationForUpdate(GetHospitalizationForUpdateRequestDto requestDto);

    SearchHospitalizationResponseDto searchHospitalization(SearchHospitalizationRequestDto requestDto);
}
