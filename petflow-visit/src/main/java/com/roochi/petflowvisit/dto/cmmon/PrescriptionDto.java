package com.roochi.petflowvisit.dto.cmmon;

import com.roochi.petflowvisit.prescription.entity.PrescriptionItem;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/13/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDto {

    private Long id;

    private Long visitId;

    private String description;

    private List<PrescriptionItemDto> items = new ArrayList<>();
}
