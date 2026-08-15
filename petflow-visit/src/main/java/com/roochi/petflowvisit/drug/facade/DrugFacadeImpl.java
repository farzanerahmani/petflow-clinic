package com.roochi.petflowvisit.drug.facade;

import com.roochi.petflowvisit.drug.service.command.DrugCommandService;
import com.roochi.petflowvisit.drug.service.query.DrugQueryService;
import com.roochi.petflowvisit.dto.request.drug.*;
import com.roochi.petflowvisit.dto.response.drug.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Component
@RequiredArgsConstructor
public class DrugFacadeImpl implements DrugFacade {

    private final DrugCommandService drugCommandService;

    private final DrugQueryService drugQueryService;

    @Override
    public AddDrugResponseDto addDrug(AddDrugRequestDto requestDto) {
        return drugCommandService.addDrug(requestDto);
    }

    @Override
    public UpdateDrugResponseDto updateDrug(UpdateDrugRequestDto requestDto) {
        return drugCommandService.updateDrug(requestDto);
    }

    @Override
    public DeleteDrugResponseDto deleteDrug(DeleteDrugRequestDto requestDto) {
        return drugCommandService.deleteDrug(requestDto);
    }

    @Override
    public GetDrugByIdResponseDto getDrugById(GetDrugByIdRequestDto requestDto) {
        return drugQueryService.getDrugById(requestDto);
    }

    @Override
    public GetDrugForUpdateResponseDto getDrugForUpdate(GetDrugForUpdateRequestDto requestDto) {
        return drugQueryService.getDrugForUpdate(requestDto);
    }

    @Override
    public SearchDrugResponseDto searchDrug(SearchDrugRequestDto requestDto) {
        return drugQueryService.searchDrug(requestDto);
    }
}
