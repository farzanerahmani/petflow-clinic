package com.roochi.petflowinventory.supplier.service.command;

import com.roochi.petflowinventory.supplier.dto.request.AddSupplierRequestDto;
import com.roochi.petflowinventory.supplier.dto.request.ChangeStatusSupplierRequestDto;
import com.roochi.petflowinventory.supplier.dto.request.UpdateSupplierRequestDto;
import com.roochi.petflowinventory.supplier.dto.response.AddSupplierResponseDto;
import com.roochi.petflowinventory.supplier.dto.response.ChangeStatusSupplierResponseDto;
import com.roochi.petflowinventory.supplier.dto.response.UpdateSupplierResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
public interface SupplierCommandService {

    AddSupplierResponseDto addSupplier(AddSupplierRequestDto requestDto);

    UpdateSupplierResponseDto updateSupplier(UpdateSupplierRequestDto requestDto);

    ChangeStatusSupplierResponseDto changeSupplierStatus(ChangeStatusSupplierRequestDto requestDto);
}
