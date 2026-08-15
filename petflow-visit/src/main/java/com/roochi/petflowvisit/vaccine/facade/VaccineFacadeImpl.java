package com.roochi.petflowvisit.vaccine.facade;

import com.roochi.petflowvisit.dto.request.vaccine.*;
import com.roochi.petflowvisit.dto.response.vaccine.*;
import com.roochi.petflowvisit.vaccine.service.command.VaccineCommandService;
import com.roochi.petflowvisit.vaccine.service.query.VaccineQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Component
@RequiredArgsConstructor
public class VaccineFacadeImpl implements VaccineFacade {

    private final VaccineCommandService vaccineCommandService;

    private final VaccineQueryService vaccineQueryService;

    @Override
    public AddVaccineResponseDto addVaccine(AddVaccineRequestDto requestDto) {
        return vaccineCommandService.addVaccine(requestDto);
    }

    @Override
    public UpdateVaccineResponseDto updateVaccine(UpdateVaccineRequestDto requestDto) {
        return vaccineCommandService.updateVaccine(requestDto);
    }

    @Override
    public DeleteVaccineResponseDto deleteVaccine(DeleteVaccineRequestDto requestDto) {
        return vaccineCommandService.deleteVaccine(requestDto);
    }

    @Override
    public GetVaccineByIdResponseDto getVaccineById(GetVaccineByIdRequestDto requestDto) {
        return vaccineQueryService.getVaccineById(requestDto);
    }

    @Override
    public GetVaccineForUpdateResponseDto getVaccineForUpdate(GetVaccineForUpdateRequestDto requestDto) {
        return vaccineQueryService.getVaccineForUpdate(requestDto);
    }

    @Override
    public SearchVaccineResponseDto searchVaccine(SearchVaccineRequestDto requestDto) {
        return vaccineQueryService.searchVaccine(requestDto);
    }
}
