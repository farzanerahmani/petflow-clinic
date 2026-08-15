package com.roochi.petflowvisit.medicalrecord.service.impl;


import com.roochi.petflowshared.security.JwtAuthentication;
import com.roochi.petflowvisit.dto.cmmon.MedicalRecordDto;
import com.roochi.petflowvisit.dto.request.medicalrecord.GetMedicalRecordByVisitIdRequestDto;
import com.roochi.petflowvisit.dto.response.medicalrecord.GetMedicalRecordByVisitIdResponseDto;
import com.roochi.petflowvisit.dto.response.visit.GetVisitByIdResponseDto;
import com.roochi.petflowvisit.medicalrecord.entity.MedicalRecord;
import com.roochi.petflowvisit.medicalrecord.mapper.MedicalRecordMapper;
import com.roochi.petflowvisit.medicalrecord.repository.MedicalRecordRepository;
import com.roochi.petflowvisit.medicalrecord.service.query.MedicalRecordQueryService;
import com.roochi.petflowvisit.visit.facade.VisitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicalRecordQueryServiceImpl implements MedicalRecordQueryService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final VisitFacade visitFacade;

    private MedicalRecordMapper medicalRecordMapper;

    @Override
    public GetMedicalRecordByVisitIdResponseDto getMedicalRecordByVisitId(GetMedicalRecordByVisitIdRequestDto requestDto) {
        JwtAuthentication authentication = (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        Long clinicId = authentication.getClinicId();

        GetVisitByIdResponseDto visit = visitFacade.getVisit(requestDto.getVisitId());

        MedicalRecord medicalRecord = medicalRecordRepository.findByIdAndClinicId(requestDto.getVisitId(), clinicId)
                .orElseThrow();

        MedicalRecordDto dto = medicalRecordMapper.toMedicalRecordDto(medicalRecord);
        dto.setVisit(visit.getVisit());
        return GetMedicalRecordByVisitIdResponseDto.builder()
                .medicalRecord(dto)
                .build();

    }
}
