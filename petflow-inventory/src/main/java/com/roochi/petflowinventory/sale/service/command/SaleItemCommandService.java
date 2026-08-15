package com.roochi.petflowinventory.sale.service.command;

import com.roochi.petflowinventory.sale.dto.request.*;
import com.roochi.petflowinventory.sale.dto.response.*;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
public interface SaleItemCommandService {

    AddSaleItemResponseDto addSaleItem(AddSaleItemRequestDto requestDto);

    UpdateSaleItemResponseDto updateSaleItem(UpdateSaleItemRequestDto requestDto);

    DeleteSaleItemResponseDto deleteSaleItem(DeleteSaleItemRequestDto requestDto);

}
