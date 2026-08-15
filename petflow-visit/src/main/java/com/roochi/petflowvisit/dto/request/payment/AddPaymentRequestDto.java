package com.roochi.petflowvisit.dto.request.payment;
import com.roochi.petflowvisit.payment.entity.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class AddPaymentRequestDto {

    @NotNull
    private Long invoiceId;

    @NotNull
    private LocalDateTime paymentDate;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private BigDecimal amount;

    @Size(max = 100)
    private String transactionReference;

    @Size(max = 100)
    private String payerName;

    @Size(max = 1000)
    private String note;

}
