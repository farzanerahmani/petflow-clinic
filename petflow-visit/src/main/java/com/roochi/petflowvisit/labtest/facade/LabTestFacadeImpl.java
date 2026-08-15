package com.roochi.petflowvisit.labtest.facade;

import com.roochi.petflowvisit.dto.request.drug.*;
import com.roochi.petflowvisit.dto.request.labtest.*;
import com.roochi.petflowvisit.dto.response.drug.*;
import com.roochi.petflowvisit.dto.response.labtest.*;
import com.roochi.petflowvisit.labtest.service.command.LabTestCommandService;
import com.roochi.petflowvisit.labtest.service.query.LabTestQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Component
@RequiredArgsConstructor
public class LabTestFacadeImpl implements LabTestFacade {

    private final LabTestCommandService labTestCommandService;

    private final LabTestQueryService labTestQueryService;

    @Override
    public AddLabTestResponseDto addLabTest(AddLabTestRequestDto requestDto) {
        return labTestCommandService.addLabTest(requestDto);
    }

    @Override
    public UpdateLabTestResponseDto updateLabTest(UpdateLabTestRequestDto requestDto) {
        return labTestCommandService.updateLabTest(requestDto);
    }

    @Override
    public DeleteLabTestResponseDto deleteLabTest(DeleteLabTestRequestDto requestDto) {
        return labTestCommandService.deleteLabTest(requestDto);
    }

    @Override
    public GetLabTestByIdResponseDto getLabTestById(GetLabTestByIdRequestDto requestDto) {
        return labTestQueryService.getLabTestById(requestDto);
    }

    @Override
    public GetLabTestForUpdateResponseDto getLabTestForUpdate(GetLabTestForUpdateRequestDto requestDto) {
        return labTestQueryService.getLabTestForUpdate(requestDto);
    }

    @Override
    public SearchLabTestResponseDto searchLabTest(SearchLabTestRequestDto requestDto) {
        return labTestQueryService.searchLabTest(requestDto);
    }
}
