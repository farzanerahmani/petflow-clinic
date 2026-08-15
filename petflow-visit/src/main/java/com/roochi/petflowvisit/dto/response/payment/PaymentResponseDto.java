package com.roochi.petflowvisit.dto.response.payment;
import com.roochi.petflowvisit.payment.entity.enums.PaymentMethod;
import com.roochi.petflowvisit.payment.entity.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDto {

    private Long id;

    private Long invoiceId;

    private LocalDateTime paymentDate;

    private PaymentMethod paymentMethod;

    private PaymentStatus status;

    private BigDecimal amount;

    private String transactionReference;

    private String payerName;

    private String note;

}
