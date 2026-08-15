package com.roochi.petflowinventory.warehouse.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/26/2026
 */
@Data
public class ChangeWarehouseStatusRequestDto {
    @NotNull
    private Long id;
}
