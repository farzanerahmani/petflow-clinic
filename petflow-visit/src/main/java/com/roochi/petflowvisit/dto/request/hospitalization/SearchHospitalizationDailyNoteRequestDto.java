package com.roochi.petflowvisit.dto.request.hospitalization;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SearchHospitalizationDailyNoteRequestDto extends PageRequestDto {

    private Long hospitalizationId;

    private Long veterinarianId;

    private LocalDateTime fromDate;

    private LocalDateTime toDate;
}
