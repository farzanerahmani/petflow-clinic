package com.roochi.petflowinventory.purchase.dto.purchase.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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
public class UpdatePurchaseRequestDto {
    @NotNull
    private Long id;
    @NotBlank
    @Size(max = 30)
    private String purchaseNumber;
    @NotNull
    private Long supplierId;
    @NotNull
    private LocalDate purchaseDate;
    @Size(max = 500)
    private String description;
}
