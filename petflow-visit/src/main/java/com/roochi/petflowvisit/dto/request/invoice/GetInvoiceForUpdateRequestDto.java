package com.roochi.petflowvisit.dto.request.invoice;

import lombok.Data;
import lombok.NonNull;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
public class GetInvoiceForUpdateRequestDto {
    @NonNull
    private Long id;
}
