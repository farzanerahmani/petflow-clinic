package com.roochi.petflowvisit.vaccine.service.query;

import com.roochi.petflowvisit.dto.request.drug.GetDrugByIdRequestDto;
import com.roochi.petflowvisit.dto.request.drug.GetDrugForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.drug.SearchDrugRequestDto;
import com.roochi.petflowvisit.dto.request.vaccine.GetVaccineByIdRequestDto;
import com.roochi.petflowvisit.dto.request.vaccine.GetVaccineForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.vaccine.SearchVaccineRequestDto;
import com.roochi.petflowvisit.dto.response.drug.GetDrugByIdResponseDto;
import com.roochi.petflowvisit.dto.response.drug.GetDrugForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.drug.SearchDrugResponseDto;
import com.roochi.petflowvisit.dto.response.vaccine.GetVaccineByIdResponseDto;
import com.roochi.petflowvisit.dto.response.vaccine.GetVaccineForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.vaccine.SearchVaccineResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
public interface VaccineQueryService {

    GetVaccineByIdResponseDto getVaccineById(GetVaccineByIdRequestDto requestDto);

    GetVaccineForUpdateResponseDto getVaccineForUpdate(GetVaccineForUpdateRequestDto requestDto);

    SearchVaccineResponseDto searchVaccine(SearchVaccineRequestDto requestDto);
}
