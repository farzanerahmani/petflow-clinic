package com.roochi.petflowvisit.payment.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.invoice.AddInvoiceRequestDto;
import com.roochi.petflowvisit.dto.request.invoice.DeleteInvoiceRequestDto;
import com.roochi.petflowvisit.dto.request.invoice.UpdateInvoiceRequestDto;
import com.roochi.petflowvisit.dto.request.payment.AddPaymentRequestDto;
import com.roochi.petflowvisit.dto.request.payment.DeletePaymentRequestDto;
import com.roochi.petflowvisit.dto.request.payment.UpdatePaymentRequestDto;
import com.roochi.petflowvisit.dto.response.payment.AddPaymentResponseDto;
import com.roochi.petflowvisit.dto.response.payment.DeletePaymentResponseDto;
import com.roochi.petflowvisit.dto.response.payment.UpdatePaymentResponseDto;
import com.roochi.petflowvisit.invoice.entity.Invoice;
import com.roochi.petflowvisit.invoice.entity.enums.InvoiceStatus;
import com.roochi.petflowvisit.invoice.repository.InvoiceRepository;
import com.roochi.petflowvisit.payment.entity.Payment;
import com.roochi.petflowvisit.payment.entity.enums.PaymentStatus;
import com.roochi.petflowvisit.payment.repository.PaymentRepository;
import com.roochi.petflowvisit.payment.service.command.PaymentCommandService;
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
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final InvoiceRepository invoiceRepository;

    private final PaymentRepository paymentRepository;

    @Override
    public AddPaymentResponseDto addPayment(AddPaymentRequestDto requestDto) {
        Invoice invoice = invoiceRepository.findByIdForUpdate(requestDto.getInvoiceId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));///InvoiceError.INVOICE_NOT_FOUND));

        BigDecimal newPaidAmount =
                invoice.getPaidAmount().add(requestDto.getAmount());

        if (newPaidAmount.compareTo(invoice.getPayableAmount()) > 0) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);//PaymentError.PAYMENT_AMOUNT_EXCEEDS_INVOICE);
        }

        Payment payment = Payment.builder()
                .invoice(invoice)
                .paymentDate(requestDto.getPaymentDate())
                .paymentMethod(requestDto.getPaymentMethod())
                .status(PaymentStatus.SUCCESS)
                .amount(requestDto.getAmount())
                .transactionReference(requestDto.getTransactionReference())
                .payerName(requestDto.getPayerName())
                .note(requestDto.getNote())
                .build();

        paymentRepository.save(payment);

        invoice.setPaidAmount(newPaidAmount);

        if (newPaidAmount.compareTo(BigDecimal.ZERO) == 0) {
            invoice.setStatus(InvoiceStatus.ISSUED);
        } else if (newPaidAmount.compareTo(invoice.getPayableAmount()) < 0) {
            invoice.setStatus(InvoiceStatus.PARTIALLY_PAID);
        } else {
            invoice.setStatus(InvoiceStatus.PAID);
        }

        invoiceRepository.save(invoice);

        return AddPaymentResponseDto.builder()
                .id(payment.getId())
                .build();
    }

    @Override
    public UpdatePaymentResponseDto updatePayment(UpdatePaymentRequestDto requestDto) {
        Payment payment = paymentRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));//PaymentError.PAYMENT_NOT_FOUND));

        Invoice invoice = payment.getInvoice();

        BigDecimal paidAmount = invoice.getPaidAmount()
                .subtract(payment.getAmount())
                .add(requestDto.getAmount());

        if (paidAmount.compareTo(invoice.getPayableAmount()) > 0) {
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);//PaymentError.PAYMENT_AMOUNT_EXCEEDS_INVOICE);
        }

        payment.setPaymentDate(requestDto.getPaymentDate());
        payment.setPaymentMethod(requestDto.getPaymentMethod());
        payment.setStatus(requestDto.getStatus());
        payment.setAmount(requestDto.getAmount());
        payment.setTransactionReference(requestDto.getTransactionReference());
        payment.setPayerName(requestDto.getPayerName());
        payment.setNote(requestDto.getNote());

        paymentRepository.save(payment);

        invoice.setPaidAmount(paidAmount);

        if (paidAmount.compareTo(BigDecimal.ZERO) == 0) {
            invoice.setStatus(InvoiceStatus.ISSUED);
        } else if (paidAmount.compareTo(invoice.getPayableAmount()) < 0) {
            invoice.setStatus(InvoiceStatus.PARTIALLY_PAID);
        } else {
            invoice.setStatus(InvoiceStatus.PAID);
        }

        return UpdatePaymentResponseDto.builder()
                .id(payment.getId())
                .build();
    }

    @Override
    public DeletePaymentResponseDto deletePayment(DeletePaymentRequestDto requestDto) {
        Payment payment = paymentRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));//PaymentError.PAYMENT_NOT_FOUND));

        Invoice invoice = payment.getInvoice();

        BigDecimal paidAmount =
                invoice.getPaidAmount().subtract(payment.getAmount());

        if (paidAmount.compareTo(BigDecimal.ZERO) < 0) {
            paidAmount = BigDecimal.ZERO;
        }

        invoice.setPaidAmount(paidAmount);

        if (paidAmount.compareTo(BigDecimal.ZERO) == 0) {
            invoice.setStatus(InvoiceStatus.ISSUED);
        } else if (paidAmount.compareTo(invoice.getPayableAmount()) < 0) {
            invoice.setStatus(InvoiceStatus.PARTIALLY_PAID);
        } else {
            invoice.setStatus(InvoiceStatus.PAID);
        }

        payment.setDeleted(true);
        payment.setDeletedAt(LocalDateTime.now());

        paymentRepository.save(payment);
        return new DeletePaymentResponseDto();
    }
}
