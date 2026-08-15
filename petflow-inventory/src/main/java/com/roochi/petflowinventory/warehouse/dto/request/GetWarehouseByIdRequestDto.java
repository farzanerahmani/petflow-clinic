package com.roochi.petflowinventory.warehouse.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
@Data
public class GetWarehouseByIdRequestDto {
    @NotNull
    private Long id;
}
