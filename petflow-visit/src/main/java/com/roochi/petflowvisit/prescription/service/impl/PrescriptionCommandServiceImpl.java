package com.roochi.petflowvisit.prescription.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.prescription.AddPrescriptionRequestDto;
import com.roochi.petflowvisit.dto.request.prescription.DeletePrescriptionRequestDto;
import com.roochi.petflowvisit.dto.request.prescription.UpdatePrescriptionRequestDto;
import com.roochi.petflowvisit.dto.response.prescription.AddPrescriptionResponseDto;
import com.roochi.petflowvisit.dto.response.prescription.DeletePrescriptionResponseDto;
import com.roochi.petflowvisit.dto.response.prescription.UpdatePrescriptionResponseDto;
import com.roochi.petflowvisit.prescription.entity.Prescription;
import com.roochi.petflowvisit.prescription.entity.PrescriptionItem;
import com.roochi.petflowvisit.prescription.repository.PrescriptionRepository;
import com.roochi.petflowvisit.prescription.service.command.PrescriptionCommandService;
import com.roochi.petflowvisit.visit.facade.VisitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/12/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PrescriptionCommandServiceImpl implements PrescriptionCommandService {
    private final PrescriptionRepository prescriptionRepository;
    private final VisitFacade visitFacade;

    @Override
    public AddPrescriptionResponseDto addPrescription(AddPrescriptionRequestDto requestDto) {
        var visit = visitFacade.getForUpdate(requestDto.getVisitId());

        if (visit.getFinishedAt() != null)
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        Prescription prescription =
                Prescription.builder()
                        .visit(visit)
                        .description(requestDto.getDescription())
                        .build();

        requestDto.getItems().forEach(item -> {
            PrescriptionItem prescriptionItem
                    = PrescriptionItem.builder()
                    .prescription(prescription)
                    .drugId(item.getDrugId())
                    .dosage(item.getDosage())
                    .doseUnitId(item.getDoseUnitId())
                    .frequencyId(item.getFrequencyId())
                    .duration(item.getDuration())
                    .durationUnitId(item.getDurationUnitId())
                    .instruction(item.getInstruction())
                    .build();
            prescription.getItems().add(prescriptionItem);
        });


        prescriptionRepository.save(prescription);

        return AddPrescriptionResponseDto.builder()
                .id(prescription.getId()).build();

    }

    @Override
    public UpdatePrescriptionResponseDto updatePrescription(UpdatePrescriptionRequestDto requestDto) {
        Prescription prescription =
                prescriptionRepository.findByVisitIdForUpdate(requestDto.getVisitId())
                        .orElseThrow();

        prescription.setDescription(requestDto.getDescription());
        prescription.getItems().clear();
        requestDto.getItems().forEach(item -> {
            PrescriptionItem prescriptionItem = PrescriptionItem.builder()
                    .prescription(prescription)
                    .drugId(item.getDrugId())
                    .dosage(item.getDosage())
                    .doseUnitId(item.getDoseUnitId())
                    .frequencyId(item.getFrequencyId())
                    .duration(item.getDuration())
                    .durationUnitId(item.getDurationUnitId())
                    .instruction(item.getInstruction())
                    .build();
            prescription.getItems().add(prescriptionItem);
        });
        prescriptionRepository.save(prescription);
        return UpdatePrescriptionResponseDto.builder()
                .id(prescription.getId())
                .build();
    }

    @Override
    public DeletePrescriptionResponseDto deletePrescription(DeletePrescriptionRequestDto responseDto) {
        Prescription prescription =
                prescriptionRepository.findByVisitIdForUpdate(responseDto.getVisitId())
                        .orElseThrow();
        prescription.setDeleted(true);
        prescription.setDeletedAt(LocalDateTime.now());
        prescriptionRepository.save(prescription);
        return new DeletePrescriptionResponseDto();
    }
}
