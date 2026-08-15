package com.roochi.petflowvisit.dto.request.invoice;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@Data
public class GetInvoiceItemForUpdateRequestDto {
    @NotNull
    private Long id;
}
