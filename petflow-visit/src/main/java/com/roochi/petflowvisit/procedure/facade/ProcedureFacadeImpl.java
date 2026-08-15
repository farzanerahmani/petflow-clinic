package com.roochi.petflowvisit.procedure.facade;

import com.roochi.petflowvisit.dto.request.procedure.*;
import com.roochi.petflowvisit.dto.response.procedure.*;
import com.roochi.petflowvisit.procedure.service.command.ProcedureCommandService;
import com.roochi.petflowvisit.procedure.service.impl.ProcedureQueryServiceImpl;
import com.roochi.petflowvisit.procedure.service.query.ProcedureQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@Component
@RequiredArgsConstructor
public class ProcedureFacadeImpl implements ProcedureFacade{

    private final ProcedureCommandService procedureCommandService;
    private final ProcedureQueryService procedureQueryService;

    @Override
    public AddProcedureResponseDto addProcedure(AddProcedureRequestDto requestDto) {
        return procedureCommandService.addProcedure(requestDto);
    }

    @Override
    public UpdateProcedureResponseDto updateProcedure(UpdateProcedureRequestDto requestDto) {
        return procedureCommandService.updateProcedure(requestDto);
    }

    @Override
    public DeleteProcedureResponseDto deleteProcedure(DeleteProcedureRequestDto requestDto) {
        return procedureCommandService.deleteProcedure(requestDto);
    }

    @Override
    public GetProcedureByIdResponseDto getProcedureById(GetProcedureByIdRequestDto requestDto) {
        return procedureQueryService.getProcedureById(requestDto);
    }

    @Override
    public GetProcedureForUpdateResponseDto getProcedureForUpdate(GetProcedureForUpdateRequestDto requestDto) {
        return procedureQueryService.getProcedureForUpdate(requestDto);
    }

    @Override
    public SearchProcedureResponseDto searchProcedure(SearchProcedureRequestDto requestDto) {
        return procedureQueryService.searchProcedure(requestDto);
    }
}
