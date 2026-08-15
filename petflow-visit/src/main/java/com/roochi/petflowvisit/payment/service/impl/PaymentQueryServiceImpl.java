package com.roochi.petflowvisit.payment.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.cmmon.PaymentDto;
import com.roochi.petflowvisit.dto.cmmon.VisitProcedureDto;
import com.roochi.petflowvisit.dto.request.payment.GetPaymentByIdRequestDto;
import com.roochi.petflowvisit.dto.request.payment.GetPaymentForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.payment.SearchPaymentRequestDto;
import com.roochi.petflowvisit.dto.response.payment.PaymentResponseDto;
import com.roochi.petflowvisit.dto.response.payment.SearchPaymentResponseDto;
import com.roochi.petflowvisit.dto.response.procedure.SearchVisitProcedureResponseDto;
import com.roochi.petflowvisit.payment.entity.Payment;
import com.roochi.petflowvisit.payment.mapper.PaymentMapper;
import com.roochi.petflowvisit.payment.repository.PaymentRepository;
import com.roochi.petflowvisit.payment.service.query.PaymentQueryService;
import com.roochi.petflowvisit.procedure.entity.VisitProcedure;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    @Override
    public PaymentResponseDto getPaymentById(GetPaymentByIdRequestDto requestDto) {
        Payment payment = paymentRepository.findById(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));//PaymentError.PAYMENT_NOT_FOUND));

        return paymentMapper.toResponseDto(payment);
    }

    @Override
    public PaymentResponseDto getPaymentForUpdate(GetPaymentForUpdateRequestDto requestDto) {
        Payment payment = paymentRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));//PaymentError.PAYMENT_NOT_FOUND));

        return paymentMapper.toResponseDto(payment);
    }

    @Override
    public SearchPaymentResponseDto searchPayment(SearchPaymentRequestDto requestDto) {

        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<Payment> page =
                paymentRepository.search(
                        requestDto.getInvoiceId(),
                        requestDto.getPaymentMethod(),
                        requestDto.getStatus(),
                        requestDto.getFromDate(),
                        requestDto.getToDate()
                        , pageRequest);


        List<PaymentDto> payments =
                page.getContent()
                        .stream()
                        .map(paymentMapper::toPaymentDto).toList();

        SearchPaymentResponseDto response = new SearchPaymentResponseDto();
        response.setResults(payments);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
