package com.roochi.petflowvisit.hospitalization.service.impl;

import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowidentity.user.repository.UserRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.hospitalization.AddHospitalizationRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.DeleteHospitalizationRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.UpdateHospitalizationRequestDto;
import com.roochi.petflowvisit.dto.response.hospitalization.AddHospitalizationResponseDto;
import com.roochi.petflowvisit.dto.response.hospitalization.DeleteHospitalizationResponseDto;
import com.roochi.petflowvisit.dto.response.hospitalization.UpdateHospitalizationResponseDto;
import com.roochi.petflowvisit.hospitalization.entity.Hospitalization;
import com.roochi.petflowvisit.hospitalization.entity.enums.HospitalizationStatus;
import com.roochi.petflowvisit.hospitalization.repository.HospitalizationRepository;
import com.roochi.petflowvisit.hospitalization.service.command.HospitalizationCommandService;
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
@Transactional
@RequiredArgsConstructor
public class HospitalizationCommandServiceImpl implements HospitalizationCommandService {

    private final HospitalizationRepository hospitalizationRepository;
    private final VisitRepository visitRepository;
    private final UserRepository userRepository;

    @Override
    public AddHospitalizationResponseDto addHospitalization(AddHospitalizationRequestDto requestDto) {
        Visit visit = visitRepository.findByIdForUpdate(requestDto.getVisitId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.VALIDATION_ERROR));

        User veterinarian = userRepository.findById(requestDto.getAttendingVeterinarianId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.VALIDATION_ERROR));
        Hospitalization hospitalization = Hospitalization.builder()
                .visit(visit)
                .admissionDate(requestDto.getAdmissionDate())
                .ward(requestDto.getWard())
                .cageNumber(requestDto.getCageNumber())
                .status(HospitalizationStatus.ADMITTED)
                .attendingVeterinarian(veterinarian)
                .diagnosis(requestDto.getDiagnosis())
                .treatmentPlan(requestDto.getTreatmentPlan())
                .note(requestDto.getNote())
                .build();

        hospitalizationRepository.save(hospitalization);
        return AddHospitalizationResponseDto.builder()
                .visitId(hospitalization.getId())
                .build();
    }

    @Override
    public UpdateHospitalizationResponseDto updateHospitalization(UpdateHospitalizationRequestDto requestDto) {
        Hospitalization hospitalization = hospitalizationRepository.
                findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.VALIDATION_ERROR));
        User veterinarian = userRepository.findById(requestDto.getAttendingVeterinarianId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.VALIDATION_ERROR));
        hospitalization.setAdmissionDate(requestDto.getAdmissionDate());
        hospitalization.setDischargeDate(requestDto.getDischargeDate());
        hospitalization.setWard(requestDto.getWard());
        hospitalization.setCageNumber(requestDto.getCageNumber());
        hospitalization.setStatus(requestDto.getStatus());
        hospitalization.setAttendingVeterinarian(veterinarian);
        hospitalization.setDiagnosis(requestDto.getDiagnosis());
        hospitalization.setTreatmentPlan(requestDto.getTreatmentPlan());
        hospitalization.setDischargeSummary(requestDto.getDischargeSummary());
        hospitalization.setNote(requestDto.getNote());
        hospitalizationRepository.save(hospitalization);
        return UpdateHospitalizationResponseDto.builder()
                .id(hospitalization.getId())
                .build();
    }

    @Override
    public DeleteHospitalizationResponseDto deleteHospitalization(DeleteHospitalizationRequestDto requestDto) {
        Hospitalization hospitalization = hospitalizationRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.VALIDATION_ERROR));

        hospitalization.setDeletedAt(LocalDateTime.now());
        hospitalization.setDeleted(true);

        hospitalizationRepository.save(hospitalization);
        return new DeleteHospitalizationResponseDto();
    }
}
