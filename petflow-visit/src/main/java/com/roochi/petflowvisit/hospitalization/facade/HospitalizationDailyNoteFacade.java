package com.roochi.petflowvisit.hospitalization.facade;

import com.roochi.petflowvisit.dto.request.hospitalization.*;
import com.roochi.petflowvisit.dto.response.hospitalization.*;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
public interface HospitalizationDailyNoteFacade {

    HospitalizationDailyNoteResponseDto getHospitalizationDailyNoteById(GetHospitalizationDailyNoteByIdRequestDto responseDto);

    AddHospitalizationDailyNoteResponseDto addHospitalizationDailyNote(AddHospitalizationDailyNoteRequestDto requestDto);

    UpdateHospitalizationDailyNoteResponseDto updateHospitalizationDailyNote(UpdateHospitalizationDailyNoteRequestDto requestDto);

    DeleteHospitalizationDailyNoteResponseDto deleteHospitalizationDailyNote(DeleteHospitalizationDailyNoteRequestDto requestDto);

    HospitalizationDailyNoteResponseDto getHospitalizationDailyNoteForUpdate(GetHospitalizationDailyNoteForUpdateRequestDto requestDto);

    SearchHospitalizationDailyNoteResponseDto searchHospitalizationDailyNote(SearchHospitalizationDailyNoteRequestDto requestDto);

}

