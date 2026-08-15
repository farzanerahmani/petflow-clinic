package com.roochi.petflowvisit.hospitalization.facade;

import com.roochi.petflowvisit.dto.request.hospitalization.*;
import com.roochi.petflowvisit.dto.response.hospitalization.*;
import com.roochi.petflowvisit.hospitalization.service.command.HospitalizationDailyNoteCommandService;
import com.roochi.petflowvisit.hospitalization.service.query.HospitalizationDailyNoteQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@Component
@RequiredArgsConstructor
public class HospitalizationDailyNoteFacadeImpl implements HospitalizationDailyNoteFacade{

    private final HospitalizationDailyNoteQueryService hospitalizationDailyNoteQueryService;
    private final HospitalizationDailyNoteCommandService hospitalizationDailyNoteCommandService;

    @Override
    public HospitalizationDailyNoteResponseDto getHospitalizationDailyNoteById(GetHospitalizationDailyNoteByIdRequestDto responseDto) {
        return hospitalizationDailyNoteQueryService.getHospitalizationDailyNoteById(responseDto);
    }

    @Override
    public AddHospitalizationDailyNoteResponseDto addHospitalizationDailyNote(AddHospitalizationDailyNoteRequestDto requestDto) {
        return hospitalizationDailyNoteCommandService.addHospitalizationDailyNote(requestDto);
    }

    @Override
    public UpdateHospitalizationDailyNoteResponseDto updateHospitalizationDailyNote(UpdateHospitalizationDailyNoteRequestDto requestDto) {
        return hospitalizationDailyNoteCommandService.updateHospitalizationDailyNote(requestDto);
    }

    @Override
    public DeleteHospitalizationDailyNoteResponseDto deleteHospitalizationDailyNote(DeleteHospitalizationDailyNoteRequestDto requestDto) {
        return hospitalizationDailyNoteCommandService.deleteHospitalizationDailyNote(requestDto);
    }

    @Override
    public HospitalizationDailyNoteResponseDto getHospitalizationDailyNoteForUpdate(GetHospitalizationDailyNoteForUpdateRequestDto requestDto) {
        return hospitalizationDailyNoteQueryService.getHHospitalizationDailyNoteForUpdate(requestDto);
    }

    @Override
    public SearchHospitalizationDailyNoteResponseDto searchHospitalizationDailyNote(SearchHospitalizationDailyNoteRequestDto requestDto) {
        return hospitalizationDailyNoteQueryService.searchHospitalizationDailyNote(requestDto);
    }
}
