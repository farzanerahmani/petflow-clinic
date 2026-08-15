package com.roochi.petflowvisit.visit.facade;

import com.roochi.petflowvisit.dto.cmmon.VisitDto;
import com.roochi.petflowvisit.dto.request.visit.*;
import com.roochi.petflowvisit.dto.response.visit.*;
import com.roochi.petflowvisit.visit.entity.Visit;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
public interface VisitFacade {

    @PostMapping(value = "/addVisit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    AddVisitResponseDto addVisit(@RequestBody AddVisitRequestDto requestDto);

    @PostMapping(value = "/getVisit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    GetVisitByIdResponseDto getVisit(Long visitId);

    @PostMapping(value = "/getAllVisits",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    GetAllVisitsResponseDto getAllVisits(@RequestBody GetAllVisitsRequestDto requestDto);

    @PostMapping(value = "/cancelVisit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    CancelVisitResponseDto cancelVisit(@RequestBody CancelVisitRequestDto requestDto);

    @PostMapping(value = "/updateVisit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UpdateVisitResponseDto updateVisit(@RequestBody UpdateVisitRequestDto requestDto);

    @PostMapping(value = "/finishVisit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    FinishVisitResponseDto finishVisit(@RequestBody FinishVisitRequestDto requestDto);

    @PostMapping(value = "/startVisit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    StartVisitResponseDto startVisit(@RequestBody StartVisitRequestDto requestDto);

    Visit getForUpdate(Long visitId);
}
