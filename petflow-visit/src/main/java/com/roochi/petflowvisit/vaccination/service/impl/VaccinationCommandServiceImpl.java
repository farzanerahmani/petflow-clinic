package com.roochi.petflowvisit.vaccination.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.vaccination.AddVaccinationRequestDto;
import com.roochi.petflowvisit.dto.request.vaccination.DeleteVaccinationRequestDto;
import com.roochi.petflowvisit.dto.request.vaccination.UpdateVaccinationRequestDto;
import com.roochi.petflowvisit.dto.response.vaccination.AddVaccinationResponseDto;
import com.roochi.petflowvisit.dto.response.vaccination.DeleteVaccinationResponseDto;
import com.roochi.petflowvisit.dto.response.vaccination.UpdateVaccinationResponseDto;
import com.roochi.petflowvisit.vaccination.entity.Vaccination;
import com.roochi.petflowvisit.vaccination.repository.VaccinationRepository;
import com.roochi.petflowvisit.vaccination.service.command.VaccinationCommandService;
import com.roochi.petflowvisit.vaccine.entity.Vaccine;
import com.roochi.petflowvisit.vaccine.repository.VaccineRepository;
import com.roochi.petflowvisit.visit.entity.Visit;
import com.roochi.petflowvisit.visit.repository.VisitRepository;
import lombok.Data;
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
public class VaccinationCommandServiceImpl implements VaccinationCommandService {

    private final VisitRepository visitRepository;
    private final VaccineRepository vaccineRepository;
    private final VaccinationRepository vaccinationRepository;

    @Override
    public AddVaccinationResponseDto addVaccination(AddVaccinationRequestDto request) {

        Visit visit = visitRepository.findByIdForUpdate(request.getVisitId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));//.VISIT_NOT_FOUND));

        Vaccine vaccine = vaccineRepository.findById(request.getVaccineId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));

        Vaccination vaccination = Vaccination.builder()
                .visit(visit)
                .vaccine(vaccine)
                .administrationDate(request.getAdministrationDate())
                .nextDueDate(request.getNextDueDate())
                .batchNumber(request.getBatchNumber())
                .administrationRoute(request.getAdministrationRoute())
                .note(request.getNote())
                .build();

        vaccinationRepository.save(vaccination);

        return AddVaccinationResponseDto.builder()
                .id(vaccination.getId()).build();

    }

    @Override
    public UpdateVaccinationResponseDto updateVaccination(UpdateVaccinationRequestDto request) {

        Vaccination vaccination = vaccinationRepository.findByIdForUpdate(request.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));

        Vaccine vaccine = vaccineRepository.findById(request.getVaccineId())
                .orElseThrow(() ->new NotFoundException(ErrorCode.INTERNAL_ERROR));

        vaccination.setVaccine(vaccine);
        vaccination.setAdministrationDate(request.getAdministrationDate());
        vaccination.setNextDueDate(request.getNextDueDate());
        vaccination.setBatchNumber(request.getBatchNumber());
        vaccination.setAdministrationRoute(request.getAdministrationRoute());
        vaccination.setNote(request.getNote());

        vaccinationRepository.save(vaccination);

        return UpdateVaccinationResponseDto.builder()
                .id(vaccination.getId()).build();
    }

    @Override
    public DeleteVaccinationResponseDto deleteVaccination(DeleteVaccinationRequestDto request) {
        Vaccination vaccination = vaccinationRepository.findByIdForUpdate(request.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        vaccination.setDeleted(true);
        vaccination.setDeletedAt(LocalDateTime.now());

        vaccinationRepository.save(vaccination);
        return new DeleteVaccinationResponseDto();
    }
}
