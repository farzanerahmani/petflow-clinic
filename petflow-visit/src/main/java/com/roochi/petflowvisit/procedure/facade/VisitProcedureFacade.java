package com.roochi.petflowvisit.procedure.facade;

import com.roochi.petflowvisit.dto.request.procedure.*;
import com.roochi.petflowvisit.dto.response.procedure.*;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
public interface VisitProcedureFacade {
    AddVisitProcedureResponseDto addVisitProcedure(AddVisitProcedureRequestDto requestDto);

    UpdateVisitProcedureResponseDto updateVisitProcedure(UpdateVisitProcedureRequestDto requestDto);

    DeleteVisitProcedureResponseDto deleteVisitProcedure(DeleteVisitProcedureRequestDto requestDto);

    VisitProcedureResponseDto getVisitProcedureById(GetVisitProcedureByIdRequestDto requestDto);

    VisitProcedureResponseDto getVisitProcedureForUpdate(GetVisitProcedureForUpdateRequestDto requestDto);

    SearchVisitProcedureResponseDto searchVisitProcedure(SearchVisitProcedureRequestDto requestDto);
}
