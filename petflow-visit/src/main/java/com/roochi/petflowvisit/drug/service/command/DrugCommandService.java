package com.roochi.petflowvisit.drug.service.command;

import com.roochi.petflowvisit.dto.request.drug.AddDrugRequestDto;
import com.roochi.petflowvisit.dto.request.drug.DeleteDrugRequestDto;
import com.roochi.petflowvisit.dto.request.drug.UpdateDrugRequestDto;
import com.roochi.petflowvisit.dto.response.drug.AddDrugResponseDto;
import com.roochi.petflowvisit.dto.response.drug.DeleteDrugResponseDto;
import com.roochi.petflowvisit.dto.response.drug.UpdateDrugResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
public interface DrugCommandService {
    AddDrugResponseDto addDrug(AddDrugRequestDto requestDto);
    UpdateDrugResponseDto updateDrug(UpdateDrugRequestDto requestDto);
    DeleteDrugResponseDto deleteDrug(DeleteDrugRequestDto requestDto);
}
