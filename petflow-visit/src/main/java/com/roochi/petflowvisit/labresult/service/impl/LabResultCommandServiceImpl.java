package com.roochi.petflowvisit.labresult.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.labresult.*;
import com.roochi.petflowvisit.dto.response.labresult.AddLabResultResponseDto;
import com.roochi.petflowvisit.dto.response.labresult.DeleteLabResultResponseDto;
import com.roochi.petflowvisit.dto.response.labresult.UpdateLabResultResponseDto;
import com.roochi.petflowvisit.labrequest.entity.LabRequest;
import com.roochi.petflowvisit.labrequest.repository.LabRequestRepository;
import com.roochi.petflowvisit.labresult.entity.LabResult;
import com.roochi.petflowvisit.labresult.entity.LabResultItem;
import com.roochi.petflowvisit.labresult.entity.LabTestParameter;
import com.roochi.petflowvisit.labresult.repository.LabResultRepository;
import com.roochi.petflowvisit.labresult.repository.LabTestParameterRepository;
import com.roochi.petflowvisit.labresult.service.command.LabResultCommandService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class LabResultCommandServiceImpl implements LabResultCommandService {

    private final LabRequestRepository labRequestRepository;
    private final LabTestParameterRepository labTestParameterRepository;
    private final LabResultRepository labResultRepository;

    @Override
    public AddLabResultResponseDto addLabResultService(AddLabResultRequestDto requestDto) {
        LabRequest labRequest = labRequestRepository
                .findByIdForUpdate(requestDto.getLabRequestId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        if (labResultRepository.findByLabRequestId(requestDto.getLabRequestId()).isPresent()) {
            new NotFoundException(ErrorCode.USER_NOT_FOUND);
        }

        LabResult labResult = LabResult.builder()
                .labRequest(labRequest)
                .resultDate(requestDto.getResultDate())
                .report(requestDto.getReport())
                .attachmentPath(requestDto.getAttachmentPath())
                .note(requestDto.getNote())
                .build();

        List<LabResultItem> items = new ArrayList<>();

        for (AddLabResultItemRequestDto itemDto : requestDto.getItems()) {

            LabTestParameter parameter =
                    labTestParameterRepository.findById(itemDto.getLabTestParameterId())
                            .orElseThrow(() ->
                                    new NotFoundException(ErrorCode.USER_NOT_FOUND));

            LabResultItem item = LabResultItem.builder()
                    .labResult(labResult)
                    .labTestParameter(parameter)
                    .resultValue(itemDto.getResultValue())
                    .flag(itemDto.getFlag())
                    .note(itemDto.getNote())
                    .build();

            items.add(item);
        }

        labResult.setItems(items);

        labResultRepository.save(labResult);

        return AddLabResultResponseDto.builder()
                .id(labResult.getId()).build();
    }

    @Override
    public UpdateLabResultResponseDto updateLabResultService(UpdateLabResultRequestDto requestDto) {
        LabResult labResult = labResultRepository
                .findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        labResult.setResultDate(requestDto.getResultDate());
        labResult.setReport(requestDto.getReport());
        labResult.setAttachmentPath(requestDto.getAttachmentPath());
        labResult.setNote(requestDto.getNote());

        labResult.getItems().clear();

        for (UpdateLabResultItemRequestDto itemDto : requestDto.getItems()) {

            LabTestParameter parameter =
                    labTestParameterRepository.findById(itemDto.getLabTestParameterId())
                            .orElseThrow(() ->
                                    new NotFoundException(ErrorCode.USER_NOT_FOUND));

            LabResultItem item = LabResultItem.builder()
                    .labResult(labResult)
                    .labTestParameter(parameter)
                    .resultValue(itemDto.getResultValue())
                    .flag(itemDto.getFlag())
                    .note(itemDto.getNote())
                    .build();

            labResult.getItems().add(item);
        }

        labResultRepository.save(labResult);

        return UpdateLabResultResponseDto.builder()
                .id(labResult.getId())
                .build();
    }

    @Override
    public DeleteLabResultResponseDto deleteLabResultService(DeleteLabResultRequestDto requestDto) {
        LabResult labResult = labResultRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        for (LabResultItem item : labResult.getItems()) {
            item.setDeleted(true);
            item.setDeletedAt(LocalDateTime.now());
        }

        labResult.setDeleted(true);
        labResult.setDeletedAt(LocalDateTime.now());

        labResultRepository.save(labResult);
        return new DeleteLabResultResponseDto();
    }
}
