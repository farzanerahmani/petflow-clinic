package com.roochi.petflowvisit.payment.service.query;

import com.roochi.petflowvisit.dto.request.payment.GetPaymentByIdRequestDto;
import com.roochi.petflowvisit.dto.request.payment.GetPaymentForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.payment.SearchPaymentRequestDto;
import com.roochi.petflowvisit.dto.response.payment.PaymentResponseDto;
import com.roochi.petflowvisit.dto.response.payment.SearchPaymentResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
public interface PaymentQueryService {

    PaymentResponseDto getPaymentById(GetPaymentByIdRequestDto requestDto);

    PaymentResponseDto getPaymentForUpdate(GetPaymentForUpdateRequestDto requestDto);

    SearchPaymentResponseDto searchPayment(SearchPaymentRequestDto requestDto);

}
