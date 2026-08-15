package com.roochi.petflowvisit.labresult.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.labresult.GetLabResultByLabRequestIdRequestDto;
import com.roochi.petflowvisit.dto.request.labresult.GetLabResultForUpdateRequestDto;
import com.roochi.petflowvisit.dto.response.labresult.GetLabResultForUpdateResponseDto;
import com.roochi.petflowvisit.dto.response.labresult.LabResultResponseDto;
import com.roochi.petflowvisit.labresult.entity.LabResult;
import com.roochi.petflowvisit.labresult.entity.ReferenceRange;
import com.roochi.petflowvisit.labresult.mapper.LabResultMapper;
import com.roochi.petflowvisit.labresult.repository.LabResultRepository;
import com.roochi.petflowvisit.labresult.repository.ReferenceRangeRepository;
import com.roochi.petflowvisit.labresult.service.query.LabResultQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LabResultQueryServiceImpl implements LabResultQueryService {


    private final LabResultRepository labResultRepository;
    private final ReferenceRangeRepository referenceRangeRepository;
    private final LabResultMapper labResultMapper;

    @Override
    public LabResultResponseDto getLabResultByLabRequestId(GetLabResultByLabRequestIdRequestDto requestDto) {


        LabResult labResult = labResultRepository.findByLabRequestId(requestDto.getLabRequestId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        LabResultResponseDto dto = labResultMapper.toResponseDto(labResult);

        dto.getItems().forEach(item -> {

            ReferenceRange referenceRange =
                    referenceRangeRepository
                            .findByParameterId(item.getLabTestParameterId())
                            .stream()
                            .findFirst()
                            .orElse(null);

            if (referenceRange != null) {

                item.setUnit(referenceRange.getUnit());

                item.setReferenceRange(
                        referenceRange.getMinimumValue()
                                + " - "
                                + referenceRange.getMaximumValue());
            }
        });

        return dto;
    }

    @Override
    public GetLabResultForUpdateResponseDto getLabResultForUpdate(GetLabResultForUpdateRequestDto requestDto) {
        return GetLabResultForUpdateResponseDto.builder()
                .labResult(labResultMapper.toLabResultDto(
                        labResultRepository.findByIdForUpdate(requestDto.getId())
                                .orElseThrow(() ->
                                        new NotFoundException(ErrorCode.USER_NOT_FOUND))
                )).build();

    }
}
