package com.roochi.petflowvisit.imaging.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.imaging.AddImagingRequestRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.DeleteImagingRequestRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.UpdateImagingRequestRequestDto;
import com.roochi.petflowvisit.dto.response.imaging.AddImagingRequestResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.DeleteImagingRequestResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.UpdateImagingRequestResponseDto;
import com.roochi.petflowvisit.imaging.entity.ImagingRequest;
import com.roochi.petflowvisit.imaging.entity.ImagingService;
import com.roochi.petflowvisit.imaging.repository.ImagingRequestRepository;
import com.roochi.petflowvisit.imaging.repository.ImagingServiceRepository;
import com.roochi.petflowvisit.imaging.service.command.ImagingRequestCommandService;
import com.roochi.petflowvisit.visit.entity.Visit;
import com.roochi.petflowvisit.visit.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ImagingRequestCommandServiceImpl implements ImagingRequestCommandService {

    private final VisitRepository visitRepository;
    private final ImagingServiceRepository imagingServiceRepository;
    private final ImagingRequestRepository imagingRequestRepository;

    @Override
    public AddImagingRequestResponseDto addImagingRequest(AddImagingRequestRequestDto requestDto) {
        Visit visit = visitRepository.findByIdForUpdate(requestDto.getVisitId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        ImagingService imagingService = imagingServiceRepository.findById(requestDto.getImagingServiceId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        ImagingRequest imagingRequest = ImagingRequest.builder()
                .visit(visit)
                .imagingService(imagingService)
                .requestDate(requestDto.getRequestDate())
                .indication(requestDto.getIndication())
                .note(requestDto.getNote())
                .build();

        imagingRequestRepository.save(imagingRequest);

        return AddImagingRequestResponseDto.builder()
                .id(imagingRequest.getId())
                .build();
    }

    @Override
    public UpdateImagingRequestResponseDto updateImagingRequest(UpdateImagingRequestRequestDto requestDto) {
        ImagingRequest imagingRequest = imagingRequestRepository
                .findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        ImagingService imagingService = imagingServiceRepository
                .findById(requestDto.getImagingServiceId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        imagingRequest.setImagingService(imagingService);
        imagingRequest.setRequestDate(requestDto.getRequestDate());
        imagingRequest.setIndication(requestDto.getIndication());
        imagingRequest.setNote(requestDto.getNote());

        imagingRequestRepository.save(imagingRequest);

        return UpdateImagingRequestResponseDto.builder()
                .id(imagingRequest.getId())
                .build();
    }

    @Override
    public DeleteImagingRequestResponseDto deleteImagingRequest(DeleteImagingRequestRequestDto requestDto) {
        ImagingRequest imagingRequest = imagingRequestRepository
                .findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));

        if (imagingRequest.getImagingResult() != null) {
            imagingRequest.getImagingResult().setDeleted(true);
            imagingRequest.getImagingResult().setDeletedAt(LocalDateTime.now());
        }

        imagingRequest.setDeleted(true);
        imagingRequest.setDeletedAt(LocalDateTime.now());

        imagingRequestRepository.save(imagingRequest);
        return new DeleteImagingRequestResponseDto();
    }
}
