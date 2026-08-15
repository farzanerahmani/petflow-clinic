package com.roochi.petflowclinic.service.impl;

import com.roochi.petflowclinic.dto.request.*;
import com.roochi.petflowclinic.dto.response.*;
import com.roochi.petflowclinic.entity.Clinic;
import com.roochi.petflowclinic.finder.ClinicFinder;
import com.roochi.petflowclinic.mapper.ClinicMapper;
import com.roochi.petflowclinic.repository.ClinicRepository;
import com.roochi.petflowclinic.service.command.ClinicCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ClinicCommandServiceImpl implements ClinicCommandService {
    private final ClinicRepository clinicRepository;
    private final ClinicMapper clinicMapper;
    private final ClinicFinder clinicFinder;

    @Override
    public CreateClinicResponseDto create(CreateClinicRequestDto requestDto) {
        Clinic clinic = clinicMapper.toEntity(requestDto);
        clinic = clinicRepository.save(clinic);
        CreateClinicResponseDto responseDto = new CreateClinicResponseDto();
        responseDto.setId(clinic.getId());
        return responseDto;
    }

    @Override
    public UpdateClinicResponseDto update(UpdateClinicRequestDto requestDto) {
        Clinic clinic = clinicFinder.findById(requestDto.getId());
        clinicMapper.update(clinic, requestDto);
        clinicRepository.save(clinic);
        return new UpdateClinicResponseDto();
    }

    @Override
    public DeleteClinicResponseDto delete(DeleteClinicRequestDto requestDto) {
        Clinic clinic = clinicFinder.findById(requestDto.getId());
        clinic.setDeleted(true);
        clinicRepository.save(clinic);
        return new DeleteClinicResponseDto();
    }

    @Override
    public ActivateClinicResponseDto activate(ActivateClinicRequestDto requestDto) {
        Clinic clinic = clinicFinder.findById(requestDto.getId());
        clinic.setActive(true);
        clinicRepository.save(clinic);
        return new ActivateClinicResponseDto();
    }

    @Override
    public DeactivateClinicResponseDto deactivate(DeactivateClinicRequestDto requestDto) {

        Clinic clinic = clinicFinder.findById(requestDto.getId());
        clinic.setActive(false);
        clinicRepository.save(clinic);
        return new DeactivateClinicResponseDto();
    }
}
