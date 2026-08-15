package com.roochi.petflowidentity.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Getter
@Setter
public class SelectClinicRequestDto {

    @NotNull
    private Long userClinicId;

    private String temporaryToken;
}
