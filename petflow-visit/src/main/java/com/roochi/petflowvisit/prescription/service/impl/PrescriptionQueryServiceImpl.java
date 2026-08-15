package com.roochi.petflowvisit.prescription.service.impl;

import com.roochi.petflowvisit.dto.cmmon.PrescriptionDto;
import com.roochi.petflowvisit.dto.cmmon.PrescriptionItemDto;
import com.roochi.petflowvisit.dto.request.prescription.GetPrescriptionByIdRequestDto;
import com.roochi.petflowvisit.dto.response.prescription.GetPrescriptionByIdResponseDto;
import com.roochi.petflowvisit.prescription.entity.Prescription;
import com.roochi.petflowvisit.prescription.entity.PrescriptionItem;
import com.roochi.petflowvisit.prescription.repository.PrescriptionRepository;
import com.roochi.petflowvisit.prescription.service.query.PrescriptionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author farzane.rahmani
 * @created 7/12/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PrescriptionQueryServiceImpl implements PrescriptionQueryService {
    private final PrescriptionRepository prescriptionRepository;

    @Override
    public GetPrescriptionByIdResponseDto getPrescriptionByVisitId(GetPrescriptionByIdRequestDto requestDto) {
        Prescription prescription = prescriptionRepository.findByVisitIdAndDeletedFalse(requestDto.getVisitId())
                .orElseThrow();

        return GetPrescriptionByIdResponseDto.builder()
                .prescription(map(prescription)).build();
    }

    @Override
    public Prescription getPrescriptionByVisitIdForUpdate(Long visitId) {
        return prescriptionRepository.findByVisitIdForUpdate(visitId)
                .orElseThrow();

    }

    private PrescriptionDto map(Prescription prescription) {
        return PrescriptionDto.builder()
                .id(prescription.getId())
                .visitId(prescription.getVisit().getId())
                .description(prescription.getDescription())
                .items(prescription.getItems().stream().map(this::mapItem).toList()).build();
    }

    private PrescriptionItemDto mapItem(PrescriptionItem item) {
        return PrescriptionItemDto.builder()
                .id(item.getId())
                .drugId(item.getDrugId())
                .dosage(item.getDosage())
                .doseUnitId(item.getDoseUnitId())
                .frequencyId(item.getFrequencyId())
                .duration(item.getDuration())
                .durationUnitId(item.getDurationUnitId())
                .instruction(item.getInstruction())
                .build();
    }
}
