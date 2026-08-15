package com.roochi.petflowvisit.dto.request.labresult;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
public class AddLabResultRequestDto {

    @NotNull
    private Long labRequestId;

    @NotNull
    private LocalDate resultDate;

    private String report;

    private String attachmentPath;

    private String note;

    @Builder.Default
    private List<AddLabResultItemRequestDto> items = new ArrayList<>();
}
