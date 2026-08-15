package com.roochi.petflowvisit.dto.cmmon;

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
public class InvoiceItemDto {

    private Long id;

    private InvoiceItemType itemType;

    private String itemName;

    private Integer quantity;

    private BigDecimal totalPrice;

}
