package com.roochi.petflowvisit.dto.response.payment;

import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import com.roochi.petflowvisit.dto.cmmon.PaymentDto;
import com.roochi.petflowvisit.dto.cmmon.VisitProcedureDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SearchPaymentResponseDto extends PageResponseDto<PaymentDto> {

}
