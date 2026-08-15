package com.roochi.petflowvisit.dto.response.invoice;

import com.roochi.petflowvisit.invoice.entity.enums.InvoiceItemType;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceItemResponseDto {

    private Long id;

    private Long invoiceId;

    private InvoiceItemType itemType;

    private String itemName;

    private Long referenceId;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal discountAmount;

    private BigDecimal totalPrice;

    private String note;

}
