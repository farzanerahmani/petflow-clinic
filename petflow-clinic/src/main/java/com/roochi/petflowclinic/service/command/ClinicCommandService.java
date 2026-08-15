package com.roochi.petflowclinic.service.command;

import com.roochi.petflowshared.service.BaseCommandService;
import com.roochi.petflowclinic.dto.request.*;
import com.roochi.petflowclinic.dto.response.*;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
public interface ClinicCommandService extends BaseCommandService<CreateClinicRequestDto,
        UpdateClinicRequestDto, DeleteClinicRequestDto, ActivateClinicRequestDto, DeactivateClinicRequestDto,
        CreateClinicResponseDto, UpdateClinicResponseDto, DeleteClinicResponseDto, ActivateClinicResponseDto, DeactivateClinicResponseDto> {
    CreateClinicResponseDto create(CreateClinicRequestDto requestDto);

    UpdateClinicResponseDto update(UpdateClinicRequestDto requestDto);

    DeleteClinicResponseDto delete(DeleteClinicRequestDto requestDto);

    ActivateClinicResponseDto activate(ActivateClinicRequestDto requestDto);

    DeactivateClinicResponseDto deactivate(DeactivateClinicRequestDto requestDto);
}
