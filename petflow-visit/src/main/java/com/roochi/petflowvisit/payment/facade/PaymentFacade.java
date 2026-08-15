package com.roochi.petflowvisit.payment.facade;

import com.roochi.petflowvisit.dto.request.payment.*;
import com.roochi.petflowvisit.dto.response.payment.*;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
public interface PaymentFacade {
    AddPaymentResponseDto addPayment(AddPaymentRequestDto requestDto);

    UpdatePaymentResponseDto updatePayment(UpdatePaymentRequestDto requestDto);

    DeletePaymentResponseDto deletePayment(DeletePaymentRequestDto requestDto);
    PaymentResponseDto getPaymentById(GetPaymentByIdRequestDto requestDto);

    PaymentResponseDto getPaymentForUpdate(GetPaymentForUpdateRequestDto requestDto);

    SearchPaymentResponseDto searchPayment(SearchPaymentRequestDto requestDto);
}
