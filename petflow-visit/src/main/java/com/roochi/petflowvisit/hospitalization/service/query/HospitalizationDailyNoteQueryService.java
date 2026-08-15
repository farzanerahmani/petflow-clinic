package com.roochi.petflowvisit.hospitalization.service.query;

import com.roochi.petflowvisit.dto.request.hospitalization.GetHospitalizationDailyNoteByIdRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.GetHospitalizationDailyNoteForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.SearchHospitalizationDailyNoteRequestDto;
import com.roochi.petflowvisit.dto.response.hospitalization.HospitalizationDailyNoteResponseDto;
import com.roochi.petflowvisit.dto.response.hospitalization.SearchHospitalizationDailyNoteResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
public interface HospitalizationDailyNoteQueryService {
    HospitalizationDailyNoteResponseDto getHospitalizationDailyNoteById(GetHospitalizationDailyNoteByIdRequestDto responseDto);

    HospitalizationDailyNoteResponseDto getHHospitalizationDailyNoteForUpdate(GetHospitalizationDailyNoteForUpdateRequestDto requestDto);

    SearchHospitalizationDailyNoteResponseDto searchHospitalizationDailyNote(SearchHospitalizationDailyNoteRequestDto requestDto);

}
