package com.roochi.petflowvisit.dto.cmmon;

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
public class InvoiceDto {

    private Long id;

    private Long visitId;

    private LocalDateTime invoiceDate;

    private InvoiceStatus status;

    private BigDecimal payableAmount;

    private BigDecimal paidAmount;
}
