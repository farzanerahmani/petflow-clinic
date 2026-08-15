package com.roochi.petflowvisit.dto.request.invoice;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@Data
public class DeleteInvoiceItemRequestDto {

    @NotNull
    private Long id;

}
