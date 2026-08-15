package com.roochi.petflowvisit.dto.request.imaging;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SearchImagingRequestRequestDto extends PageRequestDto {

    private Long visitId;

    private Long imagingServiceId;

    private LocalDate fromDate;

    private LocalDate toDate;
}
