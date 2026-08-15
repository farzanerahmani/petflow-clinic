package com.roochi.petflowvisit.procedure.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.procedure.AddProcedureRequestDto;
import com.roochi.petflowvisit.dto.request.procedure.DeleteProcedureRequestDto;
import com.roochi.petflowvisit.dto.request.procedure.UpdateProcedureRequestDto;
import com.roochi.petflowvisit.dto.response.procedure.AddProcedureResponseDto;
import com.roochi.petflowvisit.dto.response.procedure.DeleteProcedureResponseDto;
import com.roochi.petflowvisit.dto.response.procedure.UpdateProcedureResponseDto;
import com.roochi.petflowvisit.procedure.entity.Procedure;
import com.roochi.petflowvisit.procedure.repository.ProcedureRepository;
import com.roochi.petflowvisit.procedure.service.command.ProcedureCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/23/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ProcedureCommandServiceImpl implements ProcedureCommandService {
    private final ProcedureRepository procedureRepository;

    @Override
    public AddProcedureResponseDto addProcedure(AddProcedureRequestDto requestDto) {
        if (procedureRepository.existsByCodeAndDeletedFalse(requestDto.getCode())) {
            throw new NotFoundException(ErrorCode.VALIDATION_ERROR);
        }

        Procedure procedure = Procedure.builder()
                .code(requestDto.getCode())
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .active(true)
                .build();

        procedureRepository.save(procedure);

        return AddProcedureResponseDto.builder()
                .id(procedure.getId())
                .build();
    }

    @Override
    public UpdateProcedureResponseDto updateProcedure(UpdateProcedureRequestDto requestDto) {
        Procedure procedure = procedureRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        if (!procedure.getCode().equals(requestDto.getCode())
                && procedureRepository.existsByCodeAndDeletedFalse(requestDto.getCode())) {

            throw new NotFoundException(ErrorCode.VALIDATION_ERROR);
        }

        procedure.setCode(requestDto.getCode());
        procedure.setName(requestDto.getName());
        procedure.setDescription(requestDto.getDescription());
        procedure.setActive(requestDto.getActive());

        procedureRepository.save(procedure);

        return UpdateProcedureResponseDto.builder()
                .id(procedure.getId())
                .build();
    }

    @Override
    public DeleteProcedureResponseDto deleteProcedure(DeleteProcedureRequestDto requestDto) {
        Procedure procedure = procedureRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        procedure.setDeleted(true);
        procedure.setDeletedAt(LocalDateTime.now());

        procedureRepository.save(procedure);

        return new DeleteProcedureResponseDto();
    }
}
