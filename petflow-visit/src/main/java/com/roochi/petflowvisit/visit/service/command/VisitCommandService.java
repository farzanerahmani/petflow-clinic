package com.roochi.petflowvisit.visit.service.command;

import com.roochi.petflowvisit.dto.request.visit.*;
import com.roochi.petflowvisit.dto.response.visit.*;

/**
 * @author farzane.rahmani
 * @created 7/10/2026
 */
public interface VisitCommandService {

    AddVisitResponseDto addVisit(AddVisitRequestDto requestDto);

    UpdateVisitResponseDto updateVisit(UpdateVisitRequestDto requestDto);

    StartVisitResponseDto startVisit(StartVisitRequestDto requestDto);

    FinishVisitResponseDto finishVisit(FinishVisitRequestDto requestDto);

    CancelVisitResponseDto cancelVisit(CancelVisitRequestDto requestDto);

}
