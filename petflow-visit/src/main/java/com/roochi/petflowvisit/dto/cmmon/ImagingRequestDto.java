package com.roochi.petflowvisit.dto.cmmon;

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
public class ImagingRequestDto {

    private Long id;

    private String imagingServiceName;

    private LocalDate requestDate;

    private Boolean hasResult;
}
