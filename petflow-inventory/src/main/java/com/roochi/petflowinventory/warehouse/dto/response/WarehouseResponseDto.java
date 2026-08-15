package com.roochi.petflowinventory.warehouse.dto.response;

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
public class WarehouseResponseDto {

    private Long id;

    private String code;

    private String name;

    private String description;

    private String address;

    private String phoneNumber;

    private Boolean active;

    private Boolean defaultWarehouse;

}
