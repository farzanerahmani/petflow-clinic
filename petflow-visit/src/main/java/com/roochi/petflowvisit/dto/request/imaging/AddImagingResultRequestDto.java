package com.roochi.petflowvisit.dto.request.imaging;

import jakarta.validation.constraints.NotNull;
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
public class AddImagingResultRequestDto {

    @NotNull
    private Long imagingRequestId;

    @NotNull
    private LocalDate resultDate;

    private String report;

    private String attachmentPath;

    private String note;
}
