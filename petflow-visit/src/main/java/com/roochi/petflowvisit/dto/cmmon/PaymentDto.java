package com.roochi.petflowvisit.dto.cmmon;
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
public class PaymentDto {

    private Long id;

    private LocalDateTime paymentDate;

    private PaymentMethod paymentMethod;

    private PaymentStatus status;

    private BigDecimal amount;

}
