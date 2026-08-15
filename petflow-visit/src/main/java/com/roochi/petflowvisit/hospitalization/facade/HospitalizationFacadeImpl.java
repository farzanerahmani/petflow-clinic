package com.roochi.petflowvisit.hospitalization.facade;

import com.roochi.petflowvisit.dto.request.hospitalization.*;
import com.roochi.petflowvisit.dto.response.hospitalization.*;
import com.roochi.petflowvisit.hospitalization.service.command.HospitalizationCommandService;
import com.roochi.petflowvisit.hospitalization.service.query.HospitalizationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@Component
@RequiredArgsConstructor
public class HospitalizationFacadeImpl implements HospitalizationFacade{

    private final HospitalizationQueryService hospitalizationQueryService;
    private final HospitalizationCommandService hospitalizationCommandService;

    @Override
    public HospitalizationResponseDto getHospitalizationById(GetHospitalizationByIdRequestDto responseDto) {
        return hospitalizationQueryService.getHospitalizationById(responseDto);
    }

    @Override
    public AddHospitalizationResponseDto addHospitalization(AddHospitalizationRequestDto requestDto) {
        return hospitalizationCommandService.addHospitalization(requestDto);
    }

    @Override
    public UpdateHospitalizationResponseDto updateHospitalization(UpdateHospitalizationRequestDto requestDto) {
        return hospitalizationCommandService.updateHospitalization(requestDto);
    }

    @Override
    public DeleteHospitalizationResponseDto deleteHospitalization(DeleteHospitalizationRequestDto requestDto) {
        return hospitalizationCommandService.deleteHospitalization(requestDto);
    }

    @Override
    public HospitalizationResponseDto getHospitalizationForUpdate(GetHospitalizationForUpdateRequestDto requestDto) {
        return hospitalizationQueryService.getHospitalizationForUpdate(requestDto);
    }

    @Override
    public SearchHospitalizationResponseDto searchHospitalization(SearchHospitalizationRequestDto requestDto) {
        return hospitalizationQueryService.searchHospitalization(requestDto);
    }
}
