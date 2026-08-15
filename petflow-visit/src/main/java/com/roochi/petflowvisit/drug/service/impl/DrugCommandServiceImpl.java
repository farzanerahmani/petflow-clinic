package com.roochi.petflowvisit.drug.service.impl;

import com.roochi.petflowshared.exception.AlreadyExistsException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.drug.repository.DrugRepository;
import com.roochi.petflowvisit.drug.service.command.DrugCommandService;
import com.roochi.petflowvisit.dto.request.drug.AddDrugRequestDto;
import com.roochi.petflowvisit.dto.request.drug.DeleteDrugRequestDto;
import com.roochi.petflowvisit.dto.request.drug.UpdateDrugRequestDto;
import com.roochi.petflowvisit.dto.response.drug.AddDrugResponseDto;
import com.roochi.petflowvisit.dto.response.drug.DeleteDrugResponseDto;
import com.roochi.petflowvisit.dto.response.drug.UpdateDrugResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DrugCommandServiceImpl implements DrugCommandService {

    private final DrugRepository drugRepository;

    @Override
    public AddDrugResponseDto addDrug(AddDrugRequestDto requestDto) {
        if (drugRepository.existsByCode(requestDto.getCode()))
            throw new AlreadyExistsException(ErrorCode.USER_NOT_FOUND);

        Drug drug = Drug.builder()
                .code(requestDto.getCode())
                .brandName(requestDto.getBrandName())
                .genericName(requestDto.getGenericName())
                .type(requestDto.getType())
                .form(requestDto.getForm())
                .strength(requestDto.getStrength())
                .unit(requestDto.getUnit())
                .description(requestDto.getDescription())
                .prescriptionRequired(requestDto.getPrescriptionRequired())
                .active(true)
                .build();
        drugRepository.save(drug);

        return AddDrugResponseDto.builder()
                .drugId(drug.getId())
                .build();
    }

    @Override
    public UpdateDrugResponseDto updateDrug(UpdateDrugRequestDto requestDto) {
        Drug drug = drugRepository.findByIdForUpdate(requestDto.getDrugId())
                .orElseThrow();

        if (!drug.getCode().equals(requestDto.getCode()))
            throw new AlreadyExistsException(ErrorCode.USER_NOT_FOUND);

        drug.setCode(requestDto.getCode());
        drug.setBrandName(requestDto.getBrandName());
        drug.setGenericName(requestDto.getGenericName());
        drug.setType(requestDto.getType());
        drug.setForm(requestDto.getForm());
        drug.setStrength(requestDto.getStrength());
        drug.setUnit(requestDto.getUnit());
        drug.setDescription(requestDto.getDescription());
        drug.setPrescriptionRequired(requestDto.getPrescriptionRequired());
        drug.setActive(requestDto.getActive());
        drugRepository.save(drug);
        return UpdateDrugResponseDto.builder()
                .drugId(drug.getId())
                .build();
    }

    @Override
    public DeleteDrugResponseDto deleteDrug(DeleteDrugRequestDto requestDto) {
        Drug drug = drugRepository.findByIdForUpdate(requestDto.getDrugId())
                .orElseThrow();
        drug.setDeleted(true);
        drugRepository.save(drug);
        return DeleteDrugResponseDto.builder()
                .drugId(drug.getId())
                .build();
    }
}
