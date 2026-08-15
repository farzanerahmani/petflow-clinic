package com.roochi.petflowvisit.imaging.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.imaging.AddImagingResultRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.DeleteImagingResultRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.UpdateImagingResultRequestDto;
import com.roochi.petflowvisit.dto.response.imaging.AddImagingResultResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.DeleteImagingResultResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.UpdateImagingResultResponseDto;
import com.roochi.petflowvisit.imaging.entity.ImagingRequest;
import com.roochi.petflowvisit.imaging.entity.ImagingResult;
import com.roochi.petflowvisit.imaging.repository.ImagingRequestRepository;
import com.roochi.petflowvisit.imaging.repository.ImagingResultRepository;
import com.roochi.petflowvisit.imaging.service.command.ImagingResultCommandService;
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
public class ImagingResultCommandServiceImpl implements ImagingResultCommandService {

    private final ImagingResultRepository imagingResultRepository;

    private final ImagingRequestRepository imagingRequestRepository;

    @Override
    public AddImagingResultResponseDto addImagingResult(AddImagingResultRequestDto requestDto) {
        ImagingRequest imagingRequest = imagingRequestRepository
                .findByIdForUpdate(requestDto.getImagingRequestId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        if (imagingResultRepository
                .findByImagingRequestId(requestDto.getImagingRequestId())
                .isPresent()) {

            throw new NotFoundException(ErrorCode.VALIDATION_ERROR);
        }

        ImagingResult imagingResult = ImagingResult.builder()
                .imagingRequest(imagingRequest)
                .resultDate(requestDto.getResultDate())
                .report(requestDto.getReport())
                .attachmentPath(requestDto.getAttachmentPath())
                .note(requestDto.getNote())
                .build();

        imagingResultRepository.save(imagingResult);

        return AddImagingResultResponseDto.builder()
                .id(imagingResult.getId())
                .build();
    }

    @Override
    public UpdateImagingResultResponseDto updateImagingResult(UpdateImagingResultRequestDto requestDto) {
        ImagingResult imagingResult = imagingResultRepository
                .findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        imagingResult.setResultDate(requestDto.getResultDate());
        imagingResult.setReport(requestDto.getReport());
        imagingResult.setAttachmentPath(requestDto.getAttachmentPath());
        imagingResult.setNote(requestDto.getNote());

        imagingResultRepository.save(imagingResult);

        return UpdateImagingResultResponseDto.builder()
                .id(imagingResult.getId())
                .build();
    }

    @Override
    public DeleteImagingResultResponseDto deleteImagingResult(DeleteImagingResultRequestDto requestDto) {
        ImagingResult imagingResult = imagingResultRepository
                .findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        imagingResult.setDeleted(true);
        imagingResult.setDeletedAt(LocalDateTime.now());

        imagingResultRepository.save(imagingResult);

        return new DeleteImagingResultResponseDto();
    }
}
