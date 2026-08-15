package com.roochi.petflowvisit.dto.response.imaging;

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
public class ImagingServiceResponseDto {

    private Long id;

    private String code;

    private String name;

    private String description;

    private Boolean active;
}
