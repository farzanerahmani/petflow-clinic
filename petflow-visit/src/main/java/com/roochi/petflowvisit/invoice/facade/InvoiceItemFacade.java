package com.roochi.petflowvisit.invoice.facade;

import com.roochi.petflowvisit.dto.request.invoice.*;
import com.roochi.petflowvisit.dto.response.invoice.*;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
public interface InvoiceItemFacade {
    AddInvoiceItemResponseDto addInvoiceItem(AddInvoiceItemRequestDto requestDto);

    UpdateInvoiceItemResponseDto updateInvoiceItem(UpdateInvoiceItemRequestDto requestDto);

    DeleteInvoiceItemResponseDto deleteInvoiceItem(DeleteInvoiceItemRequestDto requestDto);

    InvoiceItemResponseDto getInvoiceItemById(GetInvoiceItemByIdRequestDto requestDto);

    InvoiceItemResponseDto getInvoiceItemForUpdate(GetInvoiceItemForUpdateRequestDto requestDto);

    SearchInvoiceItemResponseDto searchInvoiceItem(SearchInvoiceItemRequestDto requestDto);
}
