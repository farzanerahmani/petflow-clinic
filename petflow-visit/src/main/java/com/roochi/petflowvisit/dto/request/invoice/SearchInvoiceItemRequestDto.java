package com.roochi.petflowvisit.dto.request.invoice;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import com.roochi.petflowvisit.invoice.entity.enums.InvoiceItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SearchInvoiceItemRequestDto extends PageRequestDto {

    private Long invoiceId;

    private InvoiceItemType itemType;

    private Long referenceId;

}
