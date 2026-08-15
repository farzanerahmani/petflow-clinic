package com.roochi.petflowvisit.dto.request.invoice;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import com.roochi.petflowvisit.invoice.entity.enums.InvoiceStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */


@Schema(name = "GetAllPetsRequestDto")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SearchInvoiceRequestDto extends PageRequestDto {

    private Long visitId;

    private InvoiceStatus status;

    private LocalDateTime fromDate;

    private LocalDateTime toDate;
}
