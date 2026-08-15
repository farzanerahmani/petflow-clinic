package com.roochi.petflowvisit.labrequest.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.labrequest.AddLabRequestRequestDto;
import com.roochi.petflowvisit.dto.request.labrequest.DeleteLabRequestRequestDto;
import com.roochi.petflowvisit.dto.request.labrequest.UpdateLabRequestRequestDto;
import com.roochi.petflowvisit.dto.request.vaccination.DeleteVaccinationRequestDto;
import com.roochi.petflowvisit.dto.response.labrequest.AddLabRequestResponseDto;
import com.roochi.petflowvisit.dto.response.labrequest.DeleteLabRequestResponseDto;
import com.roochi.petflowvisit.dto.response.labrequest.UpdateLabRequestResponseDto;
import com.roochi.petflowvisit.dto.response.vaccination.DeleteVaccinationResponseDto;
import com.roochi.petflowvisit.dto.response.vaccination.UpdateVaccinationResponseDto;
import com.roochi.petflowvisit.labrequest.entity.LabRequest;
import com.roochi.petflowvisit.labrequest.repository.LabRequestRepository;
import com.roochi.petflowvisit.labrequest.service.command.LabRequestCommandService;
import com.roochi.petflowvisit.labtest.entity.LabTest;
import com.roochi.petflowvisit.labtest.repository.LabTestRepository;
import com.roochi.petflowvisit.vaccination.entity.Vaccination;
import com.roochi.petflowvisit.vaccine.entity.Vaccine;
import com.roochi.petflowvisit.visit.entity.Visit;
import com.roochi.petflowvisit.visit.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
@Service
@Transactional
@RequiredArgsConstructor
public class LabRequestCommandServiceImpl implements LabRequestCommandService {

    private final VisitRepository visitRepository;

    private final LabTestRepository labTestRepository;
    private final LabRequestRepository labRequestRepository;

    @Override
    public AddLabRequestResponseDto addLabRequest(AddLabRequestRequestDto request) {

        Visit visit = visitRepository.findByIdForUpdate(request.getVisitId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        LabTest labTest = labTestRepository.findById(request.getLabTestId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        LabRequest labRequest = LabRequest.builder()
                .visit(visit)
                .labTest(labTest)
                .requestDate(request.getRequestDate())
                .sampleDate(request.getSampleDate())
                .note(request.getNote())
                .build();

        labRequestRepository.save(labRequest);

        return AddLabRequestResponseDto.builder()
                .id(labRequest.getId())
                .build();

    }

    @Override
    public UpdateLabRequestResponseDto updateLabRequest(UpdateLabRequestRequestDto request) {

        LabRequest labRequest = labRequestRepository.findByIdForUpdate(request.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        LabTest labTest = labTestRepository.findById(request.getLabTestId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        labRequest.setLabTest(labTest);
        labRequest.setRequestDate(request.getRequestDate());
        labRequest.setSampleDate(request.getSampleDate());
        labRequest.setNote(request.getNote());

        labRequestRepository.save(labRequest);

        return UpdateLabRequestResponseDto.builder()
                .id(labRequest.getId())
                .build();
    }

    @Override
    public DeleteLabRequestResponseDto deleteLabRequest(DeleteLabRequestRequestDto request) {
        LabRequest labRequest = labRequestRepository.findByIdForUpdate(request.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        labRequest.setDeleted(true);
        labRequest.setDeletedAt(LocalDateTime.now());

        labRequestRepository.save(labRequest);
        return new DeleteLabRequestResponseDto();
    }

}
