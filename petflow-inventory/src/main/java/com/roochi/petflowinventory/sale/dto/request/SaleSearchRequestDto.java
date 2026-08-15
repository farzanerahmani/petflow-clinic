package com.roochi.petflowinventory.sale.dto.request;

import com.roochi.petflowinventory.sale.entity.enums.SaleStatus;
import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/3/2026
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SaleSearchRequestDto extends PageRequestDto {

    private String saleNumber;

    private Long warehouseId;

    private Long customerId;

    private SaleStatus status;

    private LocalDate fromDate;

    private LocalDate toDate;

}
