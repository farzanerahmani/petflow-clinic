package com.roochi.petflowvisit.dto.response.hospitalization;

import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import com.roochi.petflowvisit.dto.cmmon.HospitalizationDailyNoteDto;
import com.roochi.petflowvisit.dto.cmmon.HospitalizationDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SearchHospitalizationDailyNoteResponseDto extends PageResponseDto<HospitalizationDailyNoteDto> {
}
