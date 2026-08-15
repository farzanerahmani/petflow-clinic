package com.roochi.petflowvisit.procedure.service.command;

import com.roochi.petflowvisit.dto.request.procedure.*;
import com.roochi.petflowvisit.dto.response.procedure.*;

/**
 * @author farzane.rahmani
 * @created 7/23/2026
 */
public interface VisitProcedureCommandService {

    AddVisitProcedureResponseDto addVisitProcedure(AddVisitProcedureRequestDto requestDto);

    UpdateVisitProcedureResponseDto updateVisitProcedure(UpdateVisitProcedureRequestDto requestDto);

    DeleteVisitProcedureResponseDto deleteProcedure(DeleteVisitProcedureRequestDto requestDto);
}
