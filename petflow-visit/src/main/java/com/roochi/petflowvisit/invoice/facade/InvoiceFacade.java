package com.roochi.petflowvisit.invoice.facade;

import com.roochi.petflowvisit.dto.request.invoice.*;
import com.roochi.petflowvisit.dto.response.invoice.*;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
public interface InvoiceFacade {
    AddInvoiceResponseDto addInvoice(AddInvoiceRequestDto requestDto);

    UpdateInvoiceResponseDto updateInvoice(UpdateInvoiceRequestDto requestDto);

    DeleteInvoiceResponseDto deleteInvoice(DeleteInvoiceRequestDto requestDto);
    InvoiceResponseDto getInvoiceById(GetInvoiceByIdRequestDto requestDto);

    InvoiceResponseDto getInvoiceForUpdate(GetInvoiceForUpdateRequestDto requestDto);

    SearchInvoiceResponseDto searchInvoice(SearchInvoiceRequestDto requestDto);
}
