package com.roochi.petflowvisit.dto.request.imaging;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import lombok.*;

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
public class SearchImagingServiceRequestDto extends PageRequestDto {

    private String code;

    private String name;

    private Boolean active;
}
