package com.roochi.petflowvisit.dto.response.invoice;

import com.roochi.petflowvisit.invoice.entity.enums.InvoiceStatus;
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
public class InvoiceResponseDto {

    private Long id;

    private Long visitId;

    private LocalDateTime invoiceDate;

    private InvoiceStatus status;

    private BigDecimal totalAmount;

    private BigDecimal discountAmount;

    private BigDecimal taxAmount;

    private BigDecimal payableAmount;

    private BigDecimal paidAmount;

    private String note;
}
