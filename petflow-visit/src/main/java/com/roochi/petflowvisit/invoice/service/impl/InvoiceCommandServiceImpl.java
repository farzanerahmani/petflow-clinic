package com.roochi.petflowvisit.invoice.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.invoice.AddInvoiceRequestDto;
import com.roochi.petflowvisit.dto.request.invoice.DeleteInvoiceRequestDto;
import com.roochi.petflowvisit.dto.request.invoice.UpdateInvoiceRequestDto;
import com.roochi.petflowvisit.dto.response.invoice.AddInvoiceResponseDto;
import com.roochi.petflowvisit.dto.response.invoice.DeleteInvoiceResponseDto;
import com.roochi.petflowvisit.dto.response.invoice.UpdateInvoiceResponseDto;
import com.roochi.petflowvisit.invoice.entity.Invoice;
import com.roochi.petflowvisit.invoice.entity.enums.InvoiceStatus;
import com.roochi.petflowvisit.invoice.repository.InvoiceRepository;
import com.roochi.petflowvisit.invoice.service.command.InvoiceCommandService;
import com.roochi.petflowvisit.visit.entity.Visit;
import com.roochi.petflowvisit.visit.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class InvoiceCommandServiceImpl implements InvoiceCommandService {

    private final InvoiceRepository invoiceRepository;

    private final VisitRepository visitRepository;
    @Override
    public AddInvoiceResponseDto addInvoice(AddInvoiceRequestDto requestDto) {
        Visit visit = visitRepository.findByIdForUpdate(requestDto.getVisitId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));//VisitError.VISIT_NOT_FOUND));

        Invoice invoice = Invoice.builder()
                .visit(visit)
                .invoiceDate(requestDto.getInvoiceDate())
                .status(InvoiceStatus.DRAFT)
                .totalAmount(BigDecimal.ZERO)
                .discountAmount(requestDto.getDiscountAmount())
                .taxAmount(requestDto.getTaxAmount())
                .payableAmount(BigDecimal.ZERO)
                .paidAmount(BigDecimal.ZERO)
                .note(requestDto.getNote())
                .build();

        invoiceRepository.save(invoice);

        return AddInvoiceResponseDto.builder()
                .id(invoice.getId())
                .build();
    }

    @Override
    public UpdateInvoiceResponseDto updateInvoice(UpdateInvoiceRequestDto requestDto) {
        Invoice invoice = invoiceRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));//InvoiceError.INVOICE_NOT_FOUND));

        invoice.setInvoiceDate(requestDto.getInvoiceDate());
        invoice.setStatus(requestDto.getStatus());
        invoice.setDiscountAmount(requestDto.getDiscountAmount());
        invoice.setTaxAmount(requestDto.getTaxAmount());
        invoice.setNote(requestDto.getNote());

        invoiceRepository.save(invoice);

        return UpdateInvoiceResponseDto.builder()
                .id(invoice.getId())
                .build();
    }

    @Override
    public DeleteInvoiceResponseDto deleteInvoice(DeleteInvoiceRequestDto requestDto) {
        Invoice invoice = invoiceRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));//InvoiceError.INVOICE_NOT_FOUND));

        invoice.setDeleted(true);
        invoice.setDeletedAt(LocalDateTime.now());

        invoiceRepository.save(invoice);
        return new DeleteInvoiceResponseDto();
    }
}
