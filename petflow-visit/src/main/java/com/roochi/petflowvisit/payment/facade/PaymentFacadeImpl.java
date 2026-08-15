package com.roochi.petflowvisit.payment.facade;

import com.roochi.petflowvisit.dto.request.payment.*;
import com.roochi.petflowvisit.dto.response.payment.*;
import com.roochi.petflowvisit.payment.service.command.PaymentCommandService;
import com.roochi.petflowvisit.payment.service.query.PaymentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@Component
@RequiredArgsConstructor
public class PaymentFacadeImpl implements PaymentFacade{
    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;
    @Override
    public AddPaymentResponseDto addPayment(AddPaymentRequestDto requestDto) {
        return paymentCommandService.addPayment(requestDto);
    }

    @Override
    public UpdatePaymentResponseDto updatePayment(UpdatePaymentRequestDto requestDto) {
        return paymentCommandService.updatePayment(requestDto);
    }

    @Override
    public DeletePaymentResponseDto deletePayment(DeletePaymentRequestDto requestDto) {
        return paymentCommandService.deletePayment(requestDto);
    }

    @Override
    public PaymentResponseDto getPaymentById(GetPaymentByIdRequestDto requestDto) {
        return paymentQueryService.getPaymentById(requestDto);
    }

    @Override
    public PaymentResponseDto getPaymentForUpdate(GetPaymentForUpdateRequestDto requestDto) {
        return paymentQueryService.getPaymentForUpdate(requestDto);
    }

    @Override
    public SearchPaymentResponseDto searchPayment(SearchPaymentRequestDto requestDto) {
        return paymentQueryService.searchPayment(requestDto);
    }
}
