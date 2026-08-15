package com.roochi.petflowvisit.imaging.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.imaging.AddImagingServiceRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.DeleteImagingServiceRequestDto;
import com.roochi.petflowvisit.dto.request.imaging.UpdateImagingServiceRequestDto;
import com.roochi.petflowvisit.dto.response.imaging.AddImagingServiceResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.DeleteImagingServiceResponseDto;
import com.roochi.petflowvisit.dto.response.imaging.UpdateImagingServiceResponseDto;
import com.roochi.petflowvisit.imaging.entity.ImagingService;
import com.roochi.petflowvisit.imaging.repository.ImagingServiceRepository;
import com.roochi.petflowvisit.imaging.service.command.ImagingServiceCommandService;
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
public class ImagingServiceCommandServiceImpl implements ImagingServiceCommandService {


    private final ImagingServiceRepository imagingServiceRepository;


    @Override
    public AddImagingServiceResponseDto addImagingService(AddImagingServiceRequestDto requestDto) {
        if (imagingServiceRepository.existsByCodeAndDeletedFalse(requestDto.getCode())) {
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        }

        ImagingService imagingService = ImagingService.builder()
                .code(requestDto.getCode())
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .active(true)
                .build();

        imagingServiceRepository.save(imagingService);

        return AddImagingServiceResponseDto.builder()
                .id(imagingService.getId())
                .build();
    }

    @Override
    public UpdateImagingServiceResponseDto updateImagingService(UpdateImagingServiceRequestDto requestDto) {
        ImagingService imagingService = imagingServiceRepository
                .findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_ALREADY_EXISTS));

        if (!imagingService.getCode().equals(requestDto.getCode())
                && imagingServiceRepository.existsByCodeAndDeletedFalse(requestDto.getCode())) {

            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        }

        imagingService.setCode(requestDto.getCode());
        imagingService.setName(requestDto.getName());
        imagingService.setDescription(requestDto.getDescription());
        imagingService.setActive(requestDto.getActive());

        imagingServiceRepository.save(imagingService);

        return UpdateImagingServiceResponseDto.builder()
                .id(imagingService.getId()).build();
    }

    @Override
    public DeleteImagingServiceResponseDto deleteImagingService(DeleteImagingServiceRequestDto requestDto) {

        ImagingService imagingService = imagingServiceRepository
                .findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        imagingService.setDeleted(true);
        imagingService.setDeletedAt(LocalDateTime.now());

        imagingServiceRepository.save(imagingService);
        return new DeleteImagingServiceResponseDto();
    }
}
