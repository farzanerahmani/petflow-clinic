package com.roochi.petflowvisit.invoice.service.command;

import com.roochi.petflowvisit.dto.request.invoice.AddInvoiceItemRequestDto;
import com.roochi.petflowvisit.dto.request.invoice.DeleteInvoiceItemRequestDto;
import com.roochi.petflowvisit.dto.request.invoice.UpdateInvoiceItemRequestDto;
import com.roochi.petflowvisit.dto.response.invoice.AddInvoiceItemResponseDto;
import com.roochi.petflowvisit.dto.response.invoice.DeleteInvoiceItemResponseDto;
import com.roochi.petflowvisit.dto.response.invoice.UpdateInvoiceItemResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
public interface InvoiceItemCommandService {

    AddInvoiceItemResponseDto addInvoiceItem(AddInvoiceItemRequestDto requestDto);

    UpdateInvoiceItemResponseDto updateInvoiceItem(UpdateInvoiceItemRequestDto requestDto);

    DeleteInvoiceItemResponseDto deleteInvoiceItem(DeleteInvoiceItemRequestDto requestDto);
}
