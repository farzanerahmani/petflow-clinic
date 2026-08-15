package com.roochi.petflowvisit.dto.request.labresult;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/21/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddLabResultItemRequestDto {

    @NotNull
    private Long labTestParameterId;

    @NotBlank
    private String resultValue;

    private String flag;

    private String note;
}
