package com.roochi.petflowvisit.vaccination.facade;

import com.roochi.petflowvisit.dto.request.vaccination.*;
import com.roochi.petflowvisit.dto.request.vaccine.DeleteVaccineRequestDto;
import com.roochi.petflowvisit.dto.response.drug.GetDrugForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.vaccination.*;
import com.roochi.petflowvisit.dto.response.vaccine.DeleteVaccineResponseDto;
import com.roochi.petflowvisit.dto.response.vaccine.UpdateVaccineResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
public interface VaccinationFacade {
    AddVaccinationResponseDto addVaccination(AddVaccinationRequestDto request);

    UpdateVaccinationResponseDto updateVaccination(UpdateVaccinationRequestDto request);

    DeleteVaccinationResponseDto deleteVaccination(DeleteVaccinationRequestDto request);

    GetAllVaccinationByVisitIdResponseDto getVaccinationByVisitId(GetAllVaccinationByVisitIdRequestDto request);

    GetVaccinationForUpdateResponseDto getVaccinationForUpdate(GetVaccinationForUpdateRequestDto request);
}
