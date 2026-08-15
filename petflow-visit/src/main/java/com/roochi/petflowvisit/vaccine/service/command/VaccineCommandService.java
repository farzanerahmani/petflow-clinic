package com.roochi.petflowvisit.vaccine.service.command;

import com.roochi.petflowvisit.dto.request.vaccine.AddVaccineRequestDto;
import com.roochi.petflowvisit.dto.request.vaccine.DeleteVaccineRequestDto;
import com.roochi.petflowvisit.dto.request.vaccine.UpdateVaccineRequestDto;
import com.roochi.petflowvisit.dto.response.vaccine.AddVaccineResponseDto;
import com.roochi.petflowvisit.dto.response.vaccine.DeleteVaccineResponseDto;
import com.roochi.petflowvisit.dto.response.vaccine.UpdateVaccineResponseDto;
import com.roochi.petflowvisit.dto.response.vaccine.VaccineResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
public interface VaccineCommandService {

    AddVaccineResponseDto addVaccine(AddVaccineRequestDto requestDto);

    UpdateVaccineResponseDto updateVaccine(UpdateVaccineRequestDto requestDto);

    DeleteVaccineResponseDto deleteVaccine(DeleteVaccineRequestDto requestDto);
}
