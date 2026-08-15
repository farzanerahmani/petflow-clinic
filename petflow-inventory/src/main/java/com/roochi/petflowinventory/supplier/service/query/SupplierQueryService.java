package com.roochi.petflowinventory.supplier.service.query;

import com.roochi.petflowinventory.supplier.dto.request.*;
import com.roochi.petflowinventory.supplier.dto.response.*;
import com.roochi.petflowinventory.warehouse.dto.request.SearchWarehouseRequestDto;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
public interface SupplierQueryService {

    SupplierResponseDto getSupplierById(GetSupplierByIdRequestDto requestDto);

    SupplierResponseDto getSupplierForUpdate(GetSupplierForUpdateRequestDto requestDto);

    SearchSupplierResponseDto searchSupplier(SearchSupplierRequestDto requestDto);

    ;
}
