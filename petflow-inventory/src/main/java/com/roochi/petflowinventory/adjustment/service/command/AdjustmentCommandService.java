package com.roochi.petflowinventory.adjustment.service.command;

import com.roochi.petflowinventory.adjustment.dto.request.*;
import com.roochi.petflowinventory.adjustment.dto.response.*;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
public interface AdjustmentCommandService {

    AddAdjustmentResponseDto addAdjustment(AddAdjustmentRequestDto request);

    UpdateAdjustmentResponseDto updateAdjustment(UpdateAdjustmentRequestDto request);
}
