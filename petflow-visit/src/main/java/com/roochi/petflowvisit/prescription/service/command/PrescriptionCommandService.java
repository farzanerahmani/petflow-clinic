package com.roochi.petflowvisit.prescription.service.command;

import com.roochi.petflowvisit.dto.request.prescription.*;
import com.roochi.petflowvisit.dto.response.prescription.*;

/**
 * @author farzane.rahmani
 * @created 7/12/2026
 */
public interface PrescriptionCommandService {

    AddPrescriptionResponseDto addPrescription(AddPrescriptionRequestDto requestDto);

    UpdatePrescriptionResponseDto updatePrescription(UpdatePrescriptionRequestDto requestDto);

    DeletePrescriptionResponseDto deletePrescription(DeletePrescriptionRequestDto responseDto);

}
