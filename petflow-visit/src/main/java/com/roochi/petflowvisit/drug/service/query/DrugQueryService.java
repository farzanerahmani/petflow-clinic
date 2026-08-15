package com.roochi.petflowvisit.drug.service.query;

import com.roochi.petflowvisit.dto.request.drug.GetDrugByIdRequestDto;
import com.roochi.petflowvisit.dto.request.drug.GetDrugForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.drug.SearchDrugRequestDto;
import com.roochi.petflowvisit.dto.response.drug.GetDrugByIdResponseDto;
import com.roochi.petflowvisit.dto.response.drug.GetDrugForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.drug.SearchDrugResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
public interface DrugQueryService {
    GetDrugByIdResponseDto getDrugById(GetDrugByIdRequestDto requestDto);
    GetDrugForUpdateResponseDto getDrugForUpdate(GetDrugForUpdateRequestDto requestDto);

    SearchDrugResponseDto searchDrug(SearchDrugRequestDto requestDto);
}
