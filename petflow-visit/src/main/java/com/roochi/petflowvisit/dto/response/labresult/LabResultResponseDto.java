package com.roochi.petflowvisit.dto.response.labresult;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/21/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabResultResponseDto {

    private Long id;

    private Long labRequestId;

    private LocalDate resultDate;

    private String report;

    private String attachmentPath;

    private String note;

    private List<LabResultItemResponseDto> items;
}
