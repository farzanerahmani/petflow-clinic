package com.roochi.petflowvisit.invoice.service.query;

import com.roochi.petflowvisit.dto.request.invoice.*;
import com.roochi.petflowvisit.dto.response.invoice.InvoiceItemResponseDto;
import com.roochi.petflowvisit.dto.response.invoice.InvoiceResponseDto;
import com.roochi.petflowvisit.dto.response.invoice.SearchInvoiceItemResponseDto;
import com.roochi.petflowvisit.dto.response.invoice.SearchInvoiceResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
public interface InvoiceItemQueryService {

    InvoiceItemResponseDto getInvoiceItemById(GetInvoiceItemByIdRequestDto requestDto);

    InvoiceItemResponseDto getInvoiceItemForUpdate(GetInvoiceItemForUpdateRequestDto requestDto);

    SearchInvoiceItemResponseDto searchInvoiceItem(SearchInvoiceItemRequestDto requestDto);
}
