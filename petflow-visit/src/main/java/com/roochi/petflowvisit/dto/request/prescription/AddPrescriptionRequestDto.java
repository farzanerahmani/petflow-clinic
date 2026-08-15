package com.roochi.petflowvisit.dto.request.prescription;

import com.roochi.petflowvisit.dto.cmmon.PrescriptionItemDto;
import com.roochi.petflowvisit.prescription.entity.PrescriptionItem;
import jakarta.validation.Valid;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/12/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddPrescriptionRequestDto {

    private Long visitId;

    private String description;

    @Builder.Default
    @Valid
    List<PrescriptionItemDto> items = new ArrayList<>();
}
