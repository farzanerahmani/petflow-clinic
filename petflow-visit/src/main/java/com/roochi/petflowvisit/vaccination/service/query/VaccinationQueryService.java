package com.roochi.petflowvisit.vaccination.service.query;

import com.roochi.petflowvisit.dto.request.vaccination.*;
import com.roochi.petflowvisit.dto.response.vaccination.*;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
public interface VaccinationQueryService {

    GetAllVaccinationByVisitIdResponseDto getVaccinationByVisitId(GetAllVaccinationByVisitIdRequestDto request);

    GetVaccinationForUpdateResponseDto getVaccinationForUpdate(GetVaccinationForUpdateRequestDto request);
}
