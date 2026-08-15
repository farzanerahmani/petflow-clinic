package com.roochi.petflowvisit.medicalrecord.service.impl;

import com.roochi.petflowshared.security.JwtAuthentication;
import com.roochi.petflowvisit.dto.cmmon.MedicalRecordDto;
import com.roochi.petflowvisit.dto.request.medicalrecord.AddMedicalRecordRequestDto;
import com.roochi.petflowvisit.dto.request.medicalrecord.UpdateMedicalRecordRequestDto;
import com.roochi.petflowvisit.dto.request.visit.GetVisitByIdRequestDto;
import com.roochi.petflowvisit.dto.response.medicalrecord.AddMedicalRecordResponseDto;
import com.roochi.petflowvisit.dto.response.medicalrecord.UpdateMedicalRecordResponseDto;
import com.roochi.petflowvisit.medicalrecord.entity.MedicalRecord;
import com.roochi.petflowvisit.medicalrecord.mapper.MedicalRecordMapper;
import com.roochi.petflowvisit.medicalrecord.repository.MedicalRecordRepository;
import com.roochi.petflowvisit.medicalrecord.service.command.MedicalRecordCommandService;
import com.roochi.petflowvisit.visit.facade.VisitFacade;
import com.roochi.petflowvisit.visit.mapper.VisitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MedicalRecordCommandServiceImpl implements MedicalRecordCommandService {
    private final VisitFacade visitFacade;
    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    @Override
    public AddMedicalRecordResponseDto addMedicalRecord(AddMedicalRecordRequestDto requestDto) {

        Long clinicId = findClinicId();
        var visit =visitFacade.getVisit(requestDto.getVisitId());//age faghat baraye validation hast service exisits ezafe bashe

        medicalRecordRepository.findByVisitId(requestDto.getVisitId())
                .orElseThrow();
        MedicalRecord medicalRecord = MedicalRecord.builder()
                .clinicId(clinicId)
                .visitId(requestDto.getVisitId())
                .history(requestDto.getHistory())
                .clinicalFinding(requestDto.getClinicalFinding())
                .diagnosis(requestDto.getDiagnosis())
                .treatmentPlan(requestDto.getTreatmentPlan())
                .recommendation(requestDto.getRecommendation())
                .note(requestDto.getNote())
                .build();
        medicalRecordRepository.save(medicalRecord);
        AddMedicalRecordResponseDto responseDto = new AddMedicalRecordResponseDto();
        MedicalRecordDto medicalRecordDto=medicalRecordMapper.toMedicalRecordDto(medicalRecord);
        medicalRecordDto.setVisit(visit.getVisit());
        return responseDto;


    }

    @Override
    public UpdateMedicalRecordResponseDto updateMedicalRecord(UpdateMedicalRecordRequestDto requestDto) {

        Long clinicId = findClinicId();
        MedicalRecord medicalRecord = medicalRecordRepository.findByIdAndClinicId(requestDto.getId(), clinicId)
                .orElseThrow();
        var visit =visitFacade.getVisit(requestDto.getVisitId());
        medicalRecord.setHistory(requestDto.getHistory());
        medicalRecord.setClinicalFinding(requestDto.getClinicalFinding());
        medicalRecord.setDiagnosis(requestDto.getDiagnosis());
        medicalRecord.setTreatmentPlan(requestDto.getTreatmentPlan());
        medicalRecord.setRecommendation(requestDto.getRecommendation());
        medicalRecord.setNote(requestDto.getNote());

        medicalRecordRepository.save(medicalRecord);
        UpdateMedicalRecordResponseDto responseDto = new UpdateMedicalRecordResponseDto();
        MedicalRecordDto medicalRecordDto=medicalRecordMapper.toMedicalRecordDto(medicalRecord);
        medicalRecordDto.setVisit(visit.getVisit());
        return responseDto;
    }

    private Long findClinicId() {
        JwtAuthentication authentication = (JwtAuthentication) SecurityContextHolder.getContext()
                .getAuthentication();
        return authentication.getClinicId();

    }
}
