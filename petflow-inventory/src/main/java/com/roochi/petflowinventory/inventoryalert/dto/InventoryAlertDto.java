package com.roochi.petflowinventory.inventoryalert.dto;

import com.roochi.petflowinventory.inventoryalert.enums.AlertType;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryAlertDto {

    private AlertType type;

    private Long warehouseId;

    private String warehouseName;

    private Long drugId;

    private String drugName;

    private String batchNumber;

    private String message;

}
