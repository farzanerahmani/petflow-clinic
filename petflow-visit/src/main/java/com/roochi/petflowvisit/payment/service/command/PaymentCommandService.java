package com.roochi.petflowvisit.payment.service.command;

import com.roochi.petflowvisit.dto.request.payment.*;
import com.roochi.petflowvisit.dto.response.payment.*;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
public interface PaymentCommandService {

    AddPaymentResponseDto addPayment(AddPaymentRequestDto requestDto);

    UpdatePaymentResponseDto updatePayment(UpdatePaymentRequestDto requestDto);

    DeletePaymentResponseDto deletePayment(DeletePaymentRequestDto requestDto);
}
