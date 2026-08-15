package com.roochi.petflowinventory.alert.entity;

import com.roochi.petflowinventory.alert.entity.enums.AlertStatus;
import com.roochi.petflowinventory.alert.entity.enums.AlertType;
import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;


/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */

import java.time.LocalDateTime;

@Entity
@Table(
        name = "inventory_alert",
        indexes = {
                @Index(
                        name = "idx_alert_stock",
                        columnList = "stock_id"
                ),
                @Index(
                        name = "idx_alert_status",
                        columnList = "status"
                ),
                @Index(
                        name = "idx_alert_type",
                        columnList = "type"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AlertType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AlertStatus status;

    @Column(nullable = false, length = 500)
    private String message;

    @Column(nullable = false)
    private LocalDateTime alertDate;

    private LocalDateTime resolvedAt;

    @Column(length = 100)
    private String resolvedBy;
}
