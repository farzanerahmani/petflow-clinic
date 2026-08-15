package com.roochi.petflowvisit.drug.facade;

import com.roochi.petflowvisit.dto.request.drug.*;
import com.roochi.petflowvisit.dto.response.drug.*;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
public interface DrugFacade {

    AddDrugResponseDto addDrug(AddDrugRequestDto requestDto);

    UpdateDrugResponseDto updateDrug(UpdateDrugRequestDto requestDto);

    DeleteDrugResponseDto deleteDrug(DeleteDrugRequestDto requestDto);

    GetDrugByIdResponseDto getDrugById(GetDrugByIdRequestDto requestDto);

    GetDrugForUpdateResponseDto getDrugForUpdate(GetDrugForUpdateRequestDto requestDto);

    SearchDrugResponseDto searchDrug(SearchDrugRequestDto requestDto);
}
