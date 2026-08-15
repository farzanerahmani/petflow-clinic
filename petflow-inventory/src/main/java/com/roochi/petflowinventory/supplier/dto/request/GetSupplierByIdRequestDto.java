package com.roochi.petflowinventory.supplier.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
@Data
public class GetSupplierByIdRequestDto {
    @NotNull
    private Long id;
}
