package com.roochi.petflowvisit.visit.service.query;

import com.roochi.petflowvisit.dto.cmmon.VisitDto;
import com.roochi.petflowvisit.dto.request.visit.GetAllVisitsRequestDto;
import com.roochi.petflowvisit.dto.request.visit.GetVisitByIdRequestDto;
import com.roochi.petflowvisit.dto.response.visit.GetAllVisitsResponseDto;
import com.roochi.petflowvisit.dto.response.visit.GetVisitByIdResponseDto;
import com.roochi.petflowvisit.visit.entity.Visit;

/**
 * @author farzane.rahmani
 * @created 7/10/2026
 */
public interface VisitQueryService {

    GetVisitByIdResponseDto getVisitById(GetVisitByIdRequestDto requestDto);

    GetAllVisitsResponseDto getAllVisit(GetAllVisitsRequestDto requestDto);

    Visit getVisitForUpdate(Long visitId);
}
