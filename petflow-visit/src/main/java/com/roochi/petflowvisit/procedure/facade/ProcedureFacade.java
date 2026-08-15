package com.roochi.petflowvisit.procedure.facade;

import com.roochi.petflowvisit.dto.request.procedure.*;
import com.roochi.petflowvisit.dto.response.procedure.*;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
public interface ProcedureFacade {
    AddProcedureResponseDto addProcedure(AddProcedureRequestDto requestDto);

    UpdateProcedureResponseDto updateProcedure(UpdateProcedureRequestDto requestDto);

    DeleteProcedureResponseDto deleteProcedure(DeleteProcedureRequestDto requestDto);

    GetProcedureByIdResponseDto getProcedureById(GetProcedureByIdRequestDto requestDto);

    GetProcedureForUpdateResponseDto getProcedureForUpdate(GetProcedureForUpdateRequestDto requestDto);

    SearchProcedureResponseDto searchProcedure(SearchProcedureRequestDto requestDto);
}
