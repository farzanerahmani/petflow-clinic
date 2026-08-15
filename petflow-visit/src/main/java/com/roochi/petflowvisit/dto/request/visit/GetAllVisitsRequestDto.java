package com.roochi.petflowvisit.dto.request.visit;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import com.roochi.petflowvisit.visit.entity.enums.VisitStatus;
import com.roochi.petflowvisit.visit.entity.enums.VisitType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/10/2026
 */

@Schema(name = "GetAllPetsRequestDto")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class GetAllVisitsRequestDto extends PageRequestDto {
    private Long petId;
    private Long doctorUserId;
    private VisitStatus status;
    private VisitType type;
    private LocalDate fromDate;
    private LocalDate toDate;
}
