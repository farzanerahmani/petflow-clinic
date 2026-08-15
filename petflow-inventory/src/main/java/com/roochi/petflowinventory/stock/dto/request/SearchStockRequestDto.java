package com.roochi.petflowinventory.stock.dto.request;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SearchStockRequestDto extends PageRequestDto {
    private Long warehouseId;
    private Long drugId;
    private String batchNumber;
    private Boolean expired;
    private Boolean lowStock;
    private Boolean onlyAvailable;

}
