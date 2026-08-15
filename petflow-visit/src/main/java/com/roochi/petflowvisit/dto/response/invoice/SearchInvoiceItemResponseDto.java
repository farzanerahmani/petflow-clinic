package com.roochi.petflowvisit.dto.response.invoice;

import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import com.roochi.petflowvisit.dto.cmmon.InvoiceDto;
import com.roochi.petflowvisit.dto.cmmon.InvoiceItemDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SearchInvoiceItemResponseDto extends PageResponseDto<InvoiceItemDto> {

}
