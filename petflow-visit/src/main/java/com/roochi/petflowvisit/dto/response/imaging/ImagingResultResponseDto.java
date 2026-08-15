package com.roochi.petflowvisit.dto.response.imaging;

import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/23/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagingResultResponseDto {

    private Long id;

    private Long imagingRequestId;

    private LocalDate resultDate;

    private String report;

    private String attachmentPath;

    private String note;
}
