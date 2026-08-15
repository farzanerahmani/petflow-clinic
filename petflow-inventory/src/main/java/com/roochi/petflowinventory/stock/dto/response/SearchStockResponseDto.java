package com.roochi.petflowinventory.stock.dto.response;

import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SearchStockResponseDto extends PageResponseDto<StockResponseDto> {

}
