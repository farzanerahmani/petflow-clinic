package com.roochi.petflowinventory.alert.dto.response;

import com.roochi.petflowinventory.alert.entity.enums.AlertStatus;
import com.roochi.petflowinventory.alert.entity.enums.AlertType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Getter
@Builder
public class AlertResponseDto {

    private Long id;

    private Long stockId;

    private Long warehouseId;

    private Long drugId;

    private String batchNumber;

    private LocalDate expirationDate;

    private BigDecimal quantity;

    private BigDecimal availableQuantity;

    private AlertType type;

    private AlertStatus status;

    private String message;

    private LocalDateTime alertDate;

    private LocalDateTime resolvedAt;

    private String resolvedBy;
}
