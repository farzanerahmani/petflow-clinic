package com.roochi.petflowvisit.visit.facade;

import com.roochi.petflowvisit.dto.cmmon.VisitDto;
import com.roochi.petflowvisit.dto.request.visit.*;
import com.roochi.petflowvisit.dto.response.visit.*;
import com.roochi.petflowvisit.visit.entity.Visit;
import com.roochi.petflowvisit.visit.service.command.VisitCommandService;
import com.roochi.petflowvisit.visit.service.query.VisitQueryService;
import lombok.RequiredArgsConstructor;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
@RequiredArgsConstructor
public class VisitFacadeImpl implements VisitFacade {

    private final VisitQueryService visitQueryService;
    private final VisitCommandService visitCommandService;

    @Override
    public AddVisitResponseDto addVisit(AddVisitRequestDto requestDto) {
        return visitCommandService.addVisit(requestDto);
    }

    @Override
    public GetVisitByIdResponseDto getVisit(Long visitId) {
        return visitQueryService.getVisitById(GetVisitByIdRequestDto.builder()
                .visitId(visitId).build());
    }

    @Override
    public GetAllVisitsResponseDto getAllVisits(GetAllVisitsRequestDto requestDto) {
        return visitQueryService.getAllVisit(requestDto);
    }

    @Override
    public CancelVisitResponseDto cancelVisit(CancelVisitRequestDto requestDto) {
        return visitCommandService.cancelVisit(requestDto);
    }

    @Override
    public UpdateVisitResponseDto updateVisit(UpdateVisitRequestDto requestDto) {
        return visitCommandService.updateVisit(requestDto);
    }

    @Override
    public FinishVisitResponseDto finishVisit(FinishVisitRequestDto requestDto) {
        return visitCommandService.finishVisit(requestDto);
    }

    @Override
    public StartVisitResponseDto startVisit(StartVisitRequestDto requestDto) {
        return visitCommandService.startVisit(requestDto);
    }

    @Override
    public Visit getForUpdate(Long visitId) {
        return visitQueryService.getVisitForUpdate(visitId);
    }
}
