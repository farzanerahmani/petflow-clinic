package com.roochi.petflowvisit.procedure.service.query;

import com.roochi.petflowvisit.dto.request.procedure.*;
import com.roochi.petflowvisit.dto.response.procedure.*;

/**
 * @author farzane.rahmani
 * @created 7/23/2026
 */
public interface ProcedureQueryService {

    GetProcedureByIdResponseDto getProcedureById(GetProcedureByIdRequestDto requestDto);

    GetProcedureForUpdateResponseDto getProcedureForUpdate(GetProcedureForUpdateRequestDto requestDto);

    SearchProcedureResponseDto searchProcedure(SearchProcedureRequestDto requestDto);
}
