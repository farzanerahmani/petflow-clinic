package com.roochi.petflowinventory.purchase.dto.purchase.response;

import com.roochi.petflowinventory.purchase.entity.enums.PurchaseStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseResponseDto {
    private Long id;
    private String purchaseNumber;
    private Long supplierId;
    private String supplierName;
    private LocalDate purchaseDate;
    private PurchaseStatus status;
    private BigDecimal totalAmount;
    private String description;
}
