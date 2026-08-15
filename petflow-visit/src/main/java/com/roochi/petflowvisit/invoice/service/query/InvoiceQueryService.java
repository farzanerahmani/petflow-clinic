package com.roochi.petflowvisit.invoice.service.query;

import com.roochi.petflowvisit.dto.request.invoice.*;
import com.roochi.petflowvisit.dto.response.invoice.*;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
public interface InvoiceQueryService {

    InvoiceResponseDto getInvoiceById(GetInvoiceByIdRequestDto requestDto);

    InvoiceResponseDto getInvoiceForUpdate(GetInvoiceForUpdateRequestDto requestDto);

    SearchInvoiceResponseDto searchInvoice(SearchInvoiceRequestDto requestDto);
}
