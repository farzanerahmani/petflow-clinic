package com.roochi.petflowvisit.payment.mapper;
import com.roochi.petflowvisit.dto.cmmon.PaymentDto;
import com.roochi.petflowvisit.dto.response.payment.PaymentResponseDto;
import com.roochi.petflowvisit.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */


@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "invoiceId", source = "invoice.id")
    PaymentResponseDto toResponseDto(Payment entity);

    PaymentDto toPaymentDto(Payment entity);

    List<PaymentResponseDto> toResponseDtos(List<Payment> entities);

    List<PaymentDto> toPaymentDtos(List<Payment> entities);

}
