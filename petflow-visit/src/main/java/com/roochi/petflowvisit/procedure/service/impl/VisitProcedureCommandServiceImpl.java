package com.roochi.petflowvisit.procedure.service.impl;

import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowidentity.user.repository.UserRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.procedure.AddVisitProcedureRequestDto;
import com.roochi.petflowvisit.dto.request.procedure.DeleteVisitProcedureRequestDto;
import com.roochi.petflowvisit.dto.request.procedure.UpdateVisitProcedureRequestDto;
import com.roochi.petflowvisit.dto.response.procedure.AddVisitProcedureResponseDto;
import com.roochi.petflowvisit.dto.response.procedure.DeleteVisitProcedureResponseDto;
import com.roochi.petflowvisit.dto.response.procedure.UpdateVisitProcedureResponseDto;
import com.roochi.petflowvisit.procedure.entity.Procedure;
import com.roochi.petflowvisit.procedure.entity.VisitProcedure;
import com.roochi.petflowvisit.procedure.repository.ProcedureRepository;
import com.roochi.petflowvisit.procedure.repository.VisitProcedureRepository;
import com.roochi.petflowvisit.procedure.service.command.VisitProcedureCommandService;
import com.roochi.petflowvisit.visit.entity.Visit;
import com.roochi.petflowvisit.visit.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class VisitProcedureCommandServiceImpl implements VisitProcedureCommandService {
    private final VisitRepository visitRepository;
    private final ProcedureRepository procedureRepository;
    private final UserRepository userRepository;
    private final VisitProcedureRepository visitProcedureRepository;

    @Override
    public AddVisitProcedureResponseDto addVisitProcedure(AddVisitProcedureRequestDto requestDto) {
        Visit visit = visitRepository.findByIdForUpdate(requestDto.getVisitId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        Procedure procedure = procedureRepository.findById(requestDto.getProcedureId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        User performedBy = userRepository.findById(requestDto.getPerformedById())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        User assistant = null;
        if (requestDto.getAssistantId() != null) {
            assistant = userRepository.findById(requestDto.getAssistantId())
                    .orElseThrow(() ->
                            new NotFoundException(ErrorCode.VALIDATION_ERROR));
        }

        VisitProcedure entity = VisitProcedure.builder()
                .visit(visit)
                .procedure(procedure)
                .performedBy(performedBy)
                .assistant(assistant)
                .performedDate(requestDto.getPerformedDate())
                .durationMinutes(requestDto.getDurationMinutes())
                .cost(requestDto.getCost())
                .note(requestDto.getNote())
                .build();

        visitProcedureRepository.save(entity);

        return AddVisitProcedureResponseDto.builder()
                .id(entity.getId())
                .build();
    }

    @Override
    public UpdateVisitProcedureResponseDto updateVisitProcedure(UpdateVisitProcedureRequestDto requestDto) {
        VisitProcedure entity = visitProcedureRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        Procedure procedure = procedureRepository.findById(requestDto.getProcedureId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        User performedBy = userRepository.findById(requestDto.getPerformedById())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        User assistant = null;
        if (requestDto.getAssistantId() != null) {
            assistant = userRepository.findById(requestDto.getAssistantId())
                    .orElseThrow(() ->
                            new NotFoundException(ErrorCode.VALIDATION_ERROR));
        }

        entity.setProcedure(procedure);
        entity.setPerformedBy(performedBy);
        entity.setAssistant(assistant);
        entity.setPerformedDate(requestDto.getPerformedDate());
        entity.setDurationMinutes(requestDto.getDurationMinutes());
        entity.setCost(requestDto.getCost());
        entity.setNote(requestDto.getNote());

        visitProcedureRepository.save(entity);

        return UpdateVisitProcedureResponseDto.builder()
                .id(entity.getId()).build();
    }

    @Override
    public DeleteVisitProcedureResponseDto deleteProcedure(DeleteVisitProcedureRequestDto requestDto) {
        VisitProcedure entity = visitProcedureRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        entity.setDeletedAt(LocalDateTime.now());
        entity.setDeleted(true);

        visitProcedureRepository.save(entity);
        return new DeleteVisitProcedureResponseDto();
    }
}
