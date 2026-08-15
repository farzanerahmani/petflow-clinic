package com.roochi.petflowvisit.procedure.facade;

import com.roochi.petflowvisit.dto.request.procedure.*;
import com.roochi.petflowvisit.dto.response.procedure.*;
import com.roochi.petflowvisit.procedure.service.command.VisitProcedureCommandService;
import com.roochi.petflowvisit.procedure.service.query.VisitProcedureQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@Component
@RequiredArgsConstructor
public class VisitProcedureFacadeImpl implements VisitProcedureFacade{

    private final VisitProcedureCommandService visitProcedureCommandService;
    private final VisitProcedureQueryService visitProcedureQueryService;

    @Override
    public AddVisitProcedureResponseDto addVisitProcedure(AddVisitProcedureRequestDto requestDto) {
        return visitProcedureCommandService.addVisitProcedure(requestDto);
    }

    @Override
    public UpdateVisitProcedureResponseDto updateVisitProcedure(UpdateVisitProcedureRequestDto requestDto) {
        return visitProcedureCommandService.updateVisitProcedure(requestDto);
    }

    @Override
    public DeleteVisitProcedureResponseDto deleteVisitProcedure(DeleteVisitProcedureRequestDto requestDto) {
        return visitProcedureCommandService.deleteProcedure(requestDto);
    }

    @Override
    public VisitProcedureResponseDto getVisitProcedureById(GetVisitProcedureByIdRequestDto requestDto) {
        return visitProcedureQueryService.getVisitProcedureById(requestDto);
    }

    @Override
    public VisitProcedureResponseDto getVisitProcedureForUpdate(GetVisitProcedureForUpdateRequestDto requestDto) {
        return visitProcedureQueryService.getVisitProcedureForUpdate(requestDto);
    }

    @Override
    public SearchVisitProcedureResponseDto searchVisitProcedure(SearchVisitProcedureRequestDto requestDto) {
        return visitProcedureQueryService.searchVisitProcedure(requestDto);
    }
}
