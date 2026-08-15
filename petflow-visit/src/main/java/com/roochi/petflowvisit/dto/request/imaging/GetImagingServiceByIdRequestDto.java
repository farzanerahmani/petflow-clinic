package com.roochi.petflowvisit.dto.request.imaging;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Data
public class GetImagingServiceByIdRequestDto {

    @NotNull
    private Long id;
}
