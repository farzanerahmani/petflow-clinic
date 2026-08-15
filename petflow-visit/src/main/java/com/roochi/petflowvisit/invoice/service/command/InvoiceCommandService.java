package com.roochi.petflowvisit.invoice.service.command;

import com.roochi.petflowvisit.dto.request.invoice.*;
import com.roochi.petflowvisit.dto.response.invoice.*;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
public interface InvoiceCommandService {

    AddInvoiceResponseDto addInvoice(AddInvoiceRequestDto requestDto);

    UpdateInvoiceResponseDto updateInvoice(UpdateInvoiceRequestDto requestDto);

    DeleteInvoiceResponseDto deleteInvoice(DeleteInvoiceRequestDto requestDto);
}
