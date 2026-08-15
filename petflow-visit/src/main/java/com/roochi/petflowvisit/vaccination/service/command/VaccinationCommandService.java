package com.roochi.petflowvisit.vaccination.service.command;

import com.roochi.petflowvisit.dto.request.vaccination.AddVaccinationRequestDto;
import com.roochi.petflowvisit.dto.request.vaccination.DeleteVaccinationRequestDto;
import com.roochi.petflowvisit.dto.request.vaccination.UpdateVaccinationRequestDto;
import com.roochi.petflowvisit.dto.response.vaccination.AddVaccinationResponseDto;
import com.roochi.petflowvisit.dto.response.vaccination.DeleteVaccinationResponseDto;
import com.roochi.petflowvisit.dto.response.vaccination.UpdateVaccinationResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
public interface VaccinationCommandService {

    AddVaccinationResponseDto addVaccination(AddVaccinationRequestDto request);

    UpdateVaccinationResponseDto updateVaccination(UpdateVaccinationRequestDto request);

    DeleteVaccinationResponseDto deleteVaccination(DeleteVaccinationRequestDto request);
}
