package com.roochi.petflowvisit.medicalrecord.facade;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.cmmon.MedicalRecordDto;
import com.roochi.petflowvisit.medicalrecord.entity.MedicalRecord;
import com.roochi.petflowvisit.medicalrecord.mapper.MedicalRecordMapper;
import com.roochi.petflowvisit.medicalrecord.repository.MedicalRecordRepository;
import com.roochi.petflowvisit.visit.facade.VisitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MedicalRecordFacadeImpl implements MedicalRecordFacade{

    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;
    private final VisitFacade visitFacade;

    @Override
    public MedicalRecordDto getMedicalRecord(Long visitId) {
        MedicalRecord medicalRecord =
                medicalRecordRepository.findByVisitId(visitId)
                        .orElseThrow();
        MedicalRecordDto dto = medicalRecordMapper.toMedicalRecordDto(medicalRecord);
        dto.setVisit(visitFacade.getVisit(visitId).getVisit());
        return dto;
    }

    @Override
    public void validateMedicalRecord(Long visitId) {
        if(!medicalRecordRepository.existsByVisitId(visitId))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
    }

    @Override
    public boolean exists(Long visitId) {
        return medicalRecordRepository.existsById(visitId);
    }
}
