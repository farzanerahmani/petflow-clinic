package com.roochi.petflowvisit.dto.request.payment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Data
public class GetPaymentForUpdateRequestDto {

    @NotNull
    private Long id;
}
