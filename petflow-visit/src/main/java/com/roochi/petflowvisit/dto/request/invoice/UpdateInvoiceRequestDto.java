package com.roochi.petflowvisit.dto.request.invoice;

import com.roochi.petflowvisit.invoice.entity.enums.InvoiceStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateInvoiceRequestDto {

    @NotNull
    private Long id;

    @NotNull
    private LocalDateTime invoiceDate;

    @NotNull
    private InvoiceStatus status;

    private BigDecimal discountAmount;

    private BigDecimal taxAmount;

    @Size(max = 1000)
    private String note;
}