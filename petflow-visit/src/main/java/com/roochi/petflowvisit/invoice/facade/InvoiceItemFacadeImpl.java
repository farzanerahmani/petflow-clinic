package com.roochi.petflowvisit.invoice.facade;

import com.roochi.petflowvisit.dto.request.invoice.*;
import com.roochi.petflowvisit.dto.response.invoice.*;
import com.roochi.petflowvisit.invoice.service.command.InvoiceCommandService;
import com.roochi.petflowvisit.invoice.service.command.InvoiceItemCommandService;
import com.roochi.petflowvisit.invoice.service.query.InvoiceItemQueryService;
import com.roochi.petflowvisit.invoice.service.query.InvoiceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@Component
@RequiredArgsConstructor
public class InvoiceItemFacadeImpl implements InvoiceItemFacade{
    private final InvoiceItemCommandService invoiceItemCommandService;
    private final InvoiceItemQueryService invoiceItemQueryService;
    @Override
    public AddInvoiceItemResponseDto addInvoiceItem(AddInvoiceItemRequestDto requestDto) {
        return invoiceItemCommandService.addInvoiceItem(requestDto);
    }

    @Override
    public UpdateInvoiceItemResponseDto updateInvoiceItem(UpdateInvoiceItemRequestDto requestDto) {
        return invoiceItemCommandService.updateInvoiceItem(requestDto);
    }

    @Override
    public DeleteInvoiceItemResponseDto deleteInvoiceItem(DeleteInvoiceItemRequestDto requestDto) {
        return invoiceItemCommandService.deleteInvoiceItem(requestDto);
    }

    @Override
    public InvoiceItemResponseDto getInvoiceItemById(GetInvoiceItemByIdRequestDto requestDto) {
        return invoiceItemQueryService.getInvoiceItemById(requestDto);
    }

    @Override
    public InvoiceItemResponseDto getInvoiceItemForUpdate(GetInvoiceItemForUpdateRequestDto requestDto) {
        return invoiceItemQueryService.getInvoiceItemForUpdate(requestDto);
    }

    @Override
    public SearchInvoiceItemResponseDto searchInvoiceItem(SearchInvoiceItemRequestDto requestDto) {
        return invoiceItemQueryService.searchInvoiceItem(requestDto);
    }
}
