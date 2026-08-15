package com.roochi.petflowvisit.hospitalization.service.command;

import com.roochi.petflowvisit.dto.request.hospitalization.*;
import com.roochi.petflowvisit.dto.response.hospitalization.*;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
public interface HospitalizationDailyNoteCommandService {

    AddHospitalizationDailyNoteResponseDto addHospitalizationDailyNote(AddHospitalizationDailyNoteRequestDto requestDto);

    UpdateHospitalizationDailyNoteResponseDto updateHospitalizationDailyNote(UpdateHospitalizationDailyNoteRequestDto requestDto);

    DeleteHospitalizationDailyNoteResponseDto deleteHospitalizationDailyNote(DeleteHospitalizationDailyNoteRequestDto requestDto);
}
