package com.roochi.petflowvisit.dto.request.invoice;

import com.roochi.petflowvisit.invoice.entity.enums.InvoiceItemType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class AddInvoiceItemRequestDto {

    @NotNull
    private Long invoiceId;

    @NotNull
    private InvoiceItemType itemType;

    @NotBlank
    private String itemName;

    private Long referenceId;

    @Builder.Default
    private Integer quantity = 1;

    @NotNull
    private BigDecimal unitPrice;

    @Builder.Default
    private BigDecimal discountAmount = BigDecimal.ZERO;

    private String note;

}
