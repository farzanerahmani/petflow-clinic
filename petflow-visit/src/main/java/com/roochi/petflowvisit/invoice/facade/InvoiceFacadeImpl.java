package com.roochi.petflowvisit.invoice.facade;

import com.roochi.petflowvisit.dto.request.invoice.*;
import com.roochi.petflowvisit.dto.response.invoice.*;
import com.roochi.petflowvisit.invoice.service.command.InvoiceCommandService;
import com.roochi.petflowvisit.invoice.service.query.InvoiceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@Component
@RequiredArgsConstructor
public class InvoiceFacadeImpl implements InvoiceFacade {

    private final InvoiceCommandService invoiceCommandService;
    private final InvoiceQueryService invoiceQueryService;
    @Override
    public AddInvoiceResponseDto addInvoice(AddInvoiceRequestDto requestDto) {
        return invoiceCommandService.addInvoice(requestDto);
    }

    @Override
    public UpdateInvoiceResponseDto updateInvoice(UpdateInvoiceRequestDto requestDto) {
        return invoiceCommandService.updateInvoice(requestDto);
    }

    @Override
    public DeleteInvoiceResponseDto deleteInvoice(DeleteInvoiceRequestDto requestDto) {
        return invoiceCommandService.deleteInvoice(requestDto);
    }

    @Override
    public InvoiceResponseDto getInvoiceById(GetInvoiceByIdRequestDto requestDto) {
        return invoiceQueryService.getInvoiceById(requestDto);
    }

    @Override
    public InvoiceResponseDto getInvoiceForUpdate(GetInvoiceForUpdateRequestDto requestDto) {
        return invoiceQueryService.getInvoiceForUpdate(requestDto);
    }

    @Override
    public SearchInvoiceResponseDto searchInvoice(SearchInvoiceRequestDto requestDto) {
        return invoiceQueryService.searchInvoice(requestDto);
    }
}
