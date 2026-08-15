package com.roochi.petflowvisit.labrequest.service.query;

import com.roochi.petflowvisit.dto.request.labrequest.GetAllLabRequestByVisitIdRequestDto;
import com.roochi.petflowvisit.dto.request.labrequest.GetLabRequestForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.vaccination.GetAllVaccinationByVisitIdRequestDto;
import com.roochi.petflowvisit.dto.request.vaccination.GetVaccinationForUpdateRequestDto;
import com.roochi.petflowvisit.dto.response.labrequest.GetAllLabRequestByVisitIdResponseDto;
import com.roochi.petflowvisit.dto.response.labrequest.GetLabRequestForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.vaccination.GetAllVaccinationByVisitIdResponseDto;
import com.roochi.petflowvisit.dto.response.vaccination.GetVaccinationForUpdateResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
public interface LabRequestQueryService {

    GetAllLabRequestByVisitIdResponseDto getLabRequestByVisitId(GetAllLabRequestByVisitIdRequestDto request);

    GetLabRequestForUpdateResponseDto getLabRequestForUpdate(GetLabRequestForUpdateRequestDto request);
}
