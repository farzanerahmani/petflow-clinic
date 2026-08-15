package com.roochi.petflowvisit.dto.request.payment;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import com.roochi.petflowvisit.payment.entity.enums.PaymentMethod;
import com.roochi.petflowvisit.payment.entity.enums.PaymentStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SearchPaymentRequestDto extends PageRequestDto {

    private Long invoiceId;

    private PaymentMethod paymentMethod;

    private PaymentStatus status;

    private LocalDateTime fromDate;

    private LocalDateTime toDate;
}
