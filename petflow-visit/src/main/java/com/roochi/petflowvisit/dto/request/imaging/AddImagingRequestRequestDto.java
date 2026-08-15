package com.roochi.petflowvisit.dto.request.imaging;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class AddImagingRequestRequestDto {

    @NotNull
    private Long visitId;

    @NotNull
    private Long imagingServiceId;

    @NotNull
    private LocalDate requestDate;

    @Size(max = 1000)
    private String indication;

    @Size(max = 1000)
    private String note;
}
