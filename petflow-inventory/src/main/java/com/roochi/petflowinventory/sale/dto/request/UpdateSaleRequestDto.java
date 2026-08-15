package com.roochi.petflowinventory.sale.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateSaleRequestDto {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String saleNumber;

    @NotNull
    private Long warehouseId;

    /**
     * اگر Customer Module هنوز پیاده نشده است،
     * می‌توانید این فیلد را فعلاً حذف کنید.
     */
    private Long customerId;

    @NotNull
    private LocalDate saleDate;

    @Size(max = 500)
    private String description;

}
