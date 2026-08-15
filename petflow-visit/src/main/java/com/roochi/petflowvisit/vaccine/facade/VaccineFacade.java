package com.roochi.petflowvisit.vaccine.facade;

import com.roochi.petflowvisit.dto.request.vaccine.*;
import com.roochi.petflowvisit.dto.response.vaccine.*;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
public interface VaccineFacade {

    AddVaccineResponseDto addVaccine(AddVaccineRequestDto requestDto);

    UpdateVaccineResponseDto updateVaccine(UpdateVaccineRequestDto requestDto);

    DeleteVaccineResponseDto deleteVaccine(DeleteVaccineRequestDto requestDto);

    GetVaccineByIdResponseDto getVaccineById(GetVaccineByIdRequestDto requestDto);

    GetVaccineForUpdateResponseDto getVaccineForUpdate(GetVaccineForUpdateRequestDto requestDto);

    SearchVaccineResponseDto searchVaccine(SearchVaccineRequestDto requestDto);
}
