package com.roochi.petflowinventory.sale.dto.response;

import com.roochi.petflowinventory.sale.entity.enums.SaleStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/3/2026
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponseDto {

    private Long id;

    private String saleNumber;

    private Long warehouseId;

    private String warehouseName;

    private Long customerId;

    private String customerName;

    private LocalDate saleDate;

    private SaleStatus status;

    private BigDecimal totalAmount;

    private String description;

}
