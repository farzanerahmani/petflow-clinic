package com.roochi.petflowvisit.dto.request.payment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@Data
public class DeletePaymentRequestDto {

    @NotNull
    private Long id;
}
