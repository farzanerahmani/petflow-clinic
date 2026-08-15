package com.roochi.petflowvisit.dto.request.imaging;

import jakarta.validation.constraints.NotBlank;
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
public class AddImagingServiceRequestDto {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String description;
}
