package com.roochi.petflowinventory.sale.service.quesry;

import com.roochi.petflowinventory.sale.dto.request.SaleSearchRequestDto;
import com.roochi.petflowinventory.sale.dto.response.SaleResponseDto;
import com.roochi.petflowinventory.sale.dto.response.SearchSaleResponseDto;

/**
 * @author farzane.rahmani
 * @created 8/3/2026
 */
public interface SaleQueryService {

    SearchSaleResponseDto searchSale(SaleSearchRequestDto requestDto);

    SaleResponseDto findById(
            Long id
    );

}
