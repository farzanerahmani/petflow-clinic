package com.roochi.petflowinventory.alert.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
@Getter
@Setter
public class ResolveAlertRequestDto {

    @NotNull
    private Long alertId;
}
