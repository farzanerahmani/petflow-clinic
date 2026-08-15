package com.roochi.petflowvisit.dto.response.imaging;

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
public class ImagingRequestResponseDto {

    private Long id;

    private Long visitId;

    private Long imagingServiceId;

    private String imagingServiceName;

    private LocalDate requestDate;

    private String indication;

    private String note;

    private Boolean hasResult;
}
