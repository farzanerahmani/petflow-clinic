package com.roochi.petflowvisit.vaccine.service.impl;

import com.roochi.petflowshared.exception.AlreadyExistsException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.vaccine.AddVaccineRequestDto;
import com.roochi.petflowvisit.dto.request.vaccine.DeleteVaccineRequestDto;
import com.roochi.petflowvisit.dto.request.vaccine.UpdateVaccineRequestDto;
import com.roochi.petflowvisit.dto.response.vaccine.AddVaccineResponseDto;
import com.roochi.petflowvisit.dto.response.vaccine.DeleteVaccineResponseDto;
import com.roochi.petflowvisit.dto.response.vaccine.UpdateVaccineResponseDto;
import com.roochi.petflowvisit.vaccine.entity.Vaccine;
import com.roochi.petflowvisit.vaccine.repository.VaccineRepository;
import com.roochi.petflowvisit.vaccine.service.command.VaccineCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class VaccineCommandServiceImpl implements VaccineCommandService {

    private final VaccineRepository vaccineRepository;

    @Override
    public AddVaccineResponseDto addVaccine(AddVaccineRequestDto requestDto) {
        if (vaccineRepository.existsByCode(requestDto.getCode()))
            throw new AlreadyExistsException(ErrorCode.USER_NOT_FOUND);

        Vaccine vaccine = Vaccine.builder()
                .code(requestDto.getCode())
                .name(requestDto.getName())
                .disease(requestDto.getDisease())
                .species(requestDto.getSpecies())
                .manufacturer(requestDto.getManufacturer())
                .description(requestDto.getDescription())
                .active(true)
                .build();
        vaccineRepository.save(vaccine);

        return AddVaccineResponseDto.builder()
                .vaccineId(vaccine.getId())
                .build();
    }

    @Override
    public UpdateVaccineResponseDto updateVaccine(UpdateVaccineRequestDto requestDto) {
        Vaccine vaccine = vaccineRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow();

        if (!vaccine.getCode().equals(requestDto.getCode()))
            throw new AlreadyExistsException(ErrorCode.USER_NOT_FOUND);


        vaccine.setCode(requestDto.getCode());
        vaccine.setName(requestDto.getName());
        vaccine.setDisease(requestDto.getDisease());
        vaccine.setDisease(requestDto.getSpecies());
        vaccine.setManufacturer(requestDto.getManufacturer());
        vaccine.setDescription(requestDto.getDescription());
        vaccine.setActive(requestDto.getActive());


        vaccineRepository.save(vaccine);
        return UpdateVaccineResponseDto.builder()
                .vaccineId(vaccine.getId())
                .build();
    }

    @Override
    public DeleteVaccineResponseDto deleteVaccine(DeleteVaccineRequestDto requestDto) {
        Vaccine vaccine = vaccineRepository.findByIdForUpdate(requestDto.getVaccineId())
                .orElseThrow();
        vaccine.setDeleted(true);
        vaccineRepository.save(vaccine);
        return DeleteVaccineResponseDto.builder()
                .vaccineId(vaccine.getId())
                .build();
    }

}
