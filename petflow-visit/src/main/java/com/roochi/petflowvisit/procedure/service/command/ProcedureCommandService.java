package com.roochi.petflowvisit.procedure.service.command;

import com.roochi.petflowvisit.dto.request.procedure.AddProcedureRequestDto;
import com.roochi.petflowvisit.dto.request.procedure.DeleteProcedureRequestDto;
import com.roochi.petflowvisit.dto.request.procedure.UpdateProcedureRequestDto;
import com.roochi.petflowvisit.dto.response.procedure.AddProcedureResponseDto;
import com.roochi.petflowvisit.dto.response.procedure.DeleteProcedureResponseDto;
import com.roochi.petflowvisit.dto.response.procedure.UpdateProcedureResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/23/2026
 */
public interface ProcedureCommandService {

    AddProcedureResponseDto addProcedure(AddProcedureRequestDto requestDto);

    UpdateProcedureResponseDto updateProcedure(UpdateProcedureRequestDto requestDto);

    DeleteProcedureResponseDto deleteProcedure(DeleteProcedureRequestDto requestDto);
}
