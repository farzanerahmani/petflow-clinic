package com.roochi.petflowvisit.vaccination.facade;

import com.roochi.petflowvisit.dto.request.vaccination.*;
import com.roochi.petflowvisit.dto.request.vaccine.DeleteVaccineRequestDto;
import com.roochi.petflowvisit.dto.response.vaccination.*;
import com.roochi.petflowvisit.dto.response.vaccine.DeleteVaccineResponseDto;
import com.roochi.petflowvisit.dto.response.vaccine.UpdateVaccineResponseDto;
import com.roochi.petflowvisit.vaccination.service.command.VaccinationCommandService;
import com.roochi.petflowvisit.vaccination.service.query.VaccinationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
@Component
@RequiredArgsConstructor
public class VaccinationFacadeImpl implements VaccinationFacade{

    private final VaccinationQueryService vaccinationQueryService;
    private final VaccinationCommandService vaccinationCommandService;

    @Override
    public AddVaccinationResponseDto addVaccination(AddVaccinationRequestDto request) {
        return vaccinationCommandService.addVaccination(request);
    }

    @Override
    public UpdateVaccinationResponseDto updateVaccination(UpdateVaccinationRequestDto request) {
        return vaccinationCommandService.updateVaccination(request);
    }

    @Override
    public DeleteVaccinationResponseDto deleteVaccination(DeleteVaccinationRequestDto request) {
        return vaccinationCommandService.deleteVaccination(request);
    }

    @Override
    public GetAllVaccinationByVisitIdResponseDto getVaccinationByVisitId(GetAllVaccinationByVisitIdRequestDto request) {
        return vaccinationQueryService.getVaccinationByVisitId(request);
    }

    @Override
    public GetVaccinationForUpdateResponseDto getVaccinationForUpdate(GetVaccinationForUpdateRequestDto request) {
        return vaccinationQueryService.getVaccinationForUpdate(request);
    }
}
