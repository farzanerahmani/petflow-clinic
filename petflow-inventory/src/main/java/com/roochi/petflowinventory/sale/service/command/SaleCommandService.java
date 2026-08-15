package com.roochi.petflowinventory.sale.service.command;

import com.roochi.petflowinventory.sale.dto.request.*;
import com.roochi.petflowinventory.sale.dto.response.*;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
public interface SaleCommandService {

    AddSaleResponseDto addSale(AddSaleRequestDto requestDto);

    UpdateSaleResponseDto updateSale(UpdateSaleRequestDto requestDto);

    CompleteSaleResponseDto completeSale(CompleteSaleRequestDto requestDto);

    CancelSaleResponseDto cancelSale(CancelSaleRequestDto requestDto);

}
