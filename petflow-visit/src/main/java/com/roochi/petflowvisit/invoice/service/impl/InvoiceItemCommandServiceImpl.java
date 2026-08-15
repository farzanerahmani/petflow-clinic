package com.roochi.petflowvisit.invoice.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.invoice.AddInvoiceItemRequestDto;
import com.roochi.petflowvisit.dto.request.invoice.DeleteInvoiceItemRequestDto;
import com.roochi.petflowvisit.dto.request.invoice.UpdateInvoiceItemRequestDto;
import com.roochi.petflowvisit.dto.response.invoice.AddInvoiceItemResponseDto;
import com.roochi.petflowvisit.dto.response.invoice.DeleteInvoiceItemResponseDto;
import com.roochi.petflowvisit.dto.response.invoice.UpdateInvoiceItemResponseDto;
import com.roochi.petflowvisit.invoice.entity.Invoice;
import com.roochi.petflowvisit.invoice.entity.InvoiceItem;
import com.roochi.petflowvisit.invoice.repository.InvoiceItemRepository;
import com.roochi.petflowvisit.invoice.repository.InvoiceRepository;
import com.roochi.petflowvisit.invoice.service.command.InvoiceItemCommandService;
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
public class InvoiceItemCommandServiceImpl implements InvoiceItemCommandService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemRepository invoiceItemRepository;

    @Override
    public AddInvoiceItemResponseDto addInvoiceItem(AddInvoiceItemRequestDto requestDto) {
        Invoice invoice = invoiceRepository.findByIdForUpdate(requestDto.getInvoiceId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));//InvoiceError.INVOICE_NOT_FOUND));

        BigDecimal totalPrice =
                requestDto.getUnitPrice()
                        .multiply(BigDecimal.valueOf(requestDto.getQuantity()))
                        .subtract(requestDto.getDiscountAmount());

        InvoiceItem item = InvoiceItem.builder()
                .invoice(invoice)
                .itemType(requestDto.getItemType())
                .itemName(requestDto.getItemName())
                .referenceId(requestDto.getReferenceId())
                .quantity(requestDto.getQuantity())
                .unitPrice(requestDto.getUnitPrice())
                .discountAmount(requestDto.getDiscountAmount())
                .totalPrice(totalPrice)
                .note(requestDto.getNote())
                .build();

        invoiceItemRepository.save(item);

        BigDecimal totalAmount =
                invoiceItemRepository.findByInvoiceId(invoice.getId())
                        .stream()
                        .map(InvoiceItem::getTotalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        invoice.setTotalAmount(totalAmount);

        invoice.setPayableAmount(
                totalAmount
                        .subtract(invoice.getDiscountAmount())
                        .add(invoice.getTaxAmount()));

        invoiceRepository.save(invoice);

        return AddInvoiceItemResponseDto.builder()
                .id(item.getId())
                .build();
    }

    @Override
    public UpdateInvoiceItemResponseDto updateInvoiceItem(UpdateInvoiceItemRequestDto requestDto) {
        InvoiceItem item = invoiceItemRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));//InvoiceItemError.INVOICE_ITEM_NOT_FOUND));

        item.setItemType(requestDto.getItemType());
        item.setItemName(requestDto.getItemName());
        item.setReferenceId(requestDto.getReferenceId());
        item.setQuantity(requestDto.getQuantity());
        item.setUnitPrice(requestDto.getUnitPrice());
        item.setDiscountAmount(requestDto.getDiscountAmount());
        item.setNote(requestDto.getNote());

        BigDecimal totalPrice =
                requestDto.getUnitPrice()
                        .multiply(BigDecimal.valueOf(requestDto.getQuantity()))
                        .subtract(requestDto.getDiscountAmount());

        item.setTotalPrice(totalPrice);

        invoiceItemRepository.save(item);

        Invoice invoice = item.getInvoice();

        BigDecimal totalAmount =
                invoiceItemRepository.findByInvoiceId(invoice.getId())
                        .stream()
                        .map(InvoiceItem::getTotalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        invoice.setTotalAmount(totalAmount);

        invoice.setPayableAmount(
                totalAmount
                        .subtract(invoice.getDiscountAmount())
                        .add(invoice.getTaxAmount()));

        invoiceRepository.save(invoice);

        return UpdateInvoiceItemResponseDto.builder()
                .id(item.getId())
                .build();
    }

    @Override
    public DeleteInvoiceItemResponseDto deleteInvoiceItem(DeleteInvoiceItemRequestDto requestDto) {
        InvoiceItem item = invoiceItemRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));//InvoiceItemError.INVOICE_ITEM_NOT_FOUND));

        Invoice invoice = item.getInvoice();

        item.setDeletedAt(LocalDateTime.now());
        item.setDeleted(true);

        invoiceItemRepository.save(item);

        BigDecimal totalAmount =
                invoiceItemRepository.findByInvoiceId(invoice.getId())
                        .stream()
                        .map(InvoiceItem::getTotalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        invoice.setTotalAmount(totalAmount);

        invoice.setPayableAmount(
                totalAmount
                        .subtract(invoice.getDiscountAmount())
                        .add(invoice.getTaxAmount()));

        invoiceRepository.save(invoice);
        return new DeleteInvoiceItemResponseDto();
    }
}
