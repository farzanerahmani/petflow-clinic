package com.roochi.petflowvisit.labtest.service.impl;

import com.roochi.petflowshared.exception.AlreadyExistsException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.dto.request.drug.AddDrugRequestDto;
import com.roochi.petflowvisit.dto.request.drug.DeleteDrugRequestDto;
import com.roochi.petflowvisit.dto.request.drug.UpdateDrugRequestDto;
import com.roochi.petflowvisit.dto.request.labtest.*;
import com.roochi.petflowvisit.dto.response.drug.AddDrugResponseDto;
import com.roochi.petflowvisit.dto.response.drug.DeleteDrugResponseDto;
import com.roochi.petflowvisit.dto.response.drug.UpdateDrugResponseDto;
import com.roochi.petflowvisit.dto.response.labtest.*;
import com.roochi.petflowvisit.labtest.entity.LabTest;
import com.roochi.petflowvisit.labtest.repository.LabTestRepository;
import com.roochi.petflowvisit.labtest.service.command.LabTestCommandService;
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
public class LabTestCommandServiceImpl implements LabTestCommandService {

    private final LabTestRepository labTestRepository;

    @Override
    public AddLabTestResponseDto addLabTest(AddLabTestRequestDto requestDto) {
        if (labTestRepository.existsByCode(requestDto.getCode()))
            throw new AlreadyExistsException(ErrorCode.USER_NOT_FOUND);

        LabTest labTest = LabTest.builder()
                .code(requestDto.getCode())
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .active(true)
                .build();
        labTestRepository.save(labTest);

        return AddLabTestResponseDto.builder()
                .id(labTest.getId())
                .build();
    }

    @Override
    public UpdateLabTestResponseDto updateLabTest(UpdateLabTestRequestDto requestDto) {
        LabTest labTest = labTestRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow();

        if (!labTest.getCode().equals(requestDto.getCode()))
            throw new AlreadyExistsException(ErrorCode.USER_NOT_FOUND);

        labTest.setCode(requestDto.getCode());
        labTest.setName(requestDto.getName());
        labTest.setDescription(requestDto.getDescription());
        labTest.setActive(requestDto.getActive());
        labTestRepository.save(labTest);
        return UpdateLabTestResponseDto.builder()
                .id(labTest.getId())
                .build();
    }

    @Override
    public DeleteLabTestResponseDto deleteLabTest(DeleteLabTestRequestDto requestDto) {
        LabTest labTest = labTestRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow();
        labTest.setDeleted(true);
        labTestRepository.save(labTest);
        return DeleteLabTestResponseDto.builder()
                .id(labTest.getId())
                .build();
    }
}
