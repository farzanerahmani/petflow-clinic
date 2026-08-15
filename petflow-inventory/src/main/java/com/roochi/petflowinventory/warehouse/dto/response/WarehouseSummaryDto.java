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
public class WarehouseSummaryDto {

    private Long id;

    private String code;

    private String name;

    private Boolean active;

}
