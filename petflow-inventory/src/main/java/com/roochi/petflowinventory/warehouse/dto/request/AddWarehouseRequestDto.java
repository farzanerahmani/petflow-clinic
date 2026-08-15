package com.roochi.petflowinventory.warehouse.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/26/2026
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddWarehouseRequestDto {

    @NotBlank
    @Size(max = 30)
    private String code;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String description;

    @Size(max = 300)
    private String address;

    @Size(max = 20)
    private String phoneNumber;

    private Boolean active;

    private Boolean defaultWarehouse;

}
