package com.roochi.petflowinventory.sale.dto.response;

import com.roochi.petflowinventory.warehouse.dto.response.WarehouseSummaryDto;
import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 8/4/2026
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SearchSaleResponseDto extends PageResponseDto<SaleResponseDto> {
}
