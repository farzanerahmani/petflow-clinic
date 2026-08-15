package com.roochi.petflowinventory.supplier.service.impl;

import com.roochi.petflowinventory.supplier.dto.request.AddSupplierRequestDto;
import com.roochi.petflowinventory.supplier.dto.request.ChangeStatusSupplierRequestDto;
import com.roochi.petflowinventory.supplier.dto.request.UpdateSupplierRequestDto;
import com.roochi.petflowinventory.supplier.dto.response.AddSupplierResponseDto;
import com.roochi.petflowinventory.supplier.dto.response.ChangeStatusSupplierResponseDto;
import com.roochi.petflowinventory.supplier.dto.response.UpdateSupplierResponseDto;
import com.roochi.petflowinventory.supplier.entity.Supplier;
import com.roochi.petflowinventory.supplier.repository.SupplierRepository;
import com.roochi.petflowinventory.supplier.service.command.SupplierCommandService;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class SupplierCommandServiceImpl implements SupplierCommandService {
    private final SupplierRepository supplierRepository;

    @Override
    public AddSupplierResponseDto addSupplier(AddSupplierRequestDto requestDto) {
        supplierRepository.findByCode(requestDto.getCode()).ifPresent(supplier -> {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
        });
        // SupplierError.SUPPLIER_CODE_ALREADY_EXISTS);
        // });
        Supplier supplier = Supplier.builder()
                .code(requestDto.getCode())
                .name(requestDto.getName())
                .contactPerson(requestDto.getContactPerson())
                .phoneNumber(requestDto.getPhoneNumber())
                .email(requestDto.getEmail())
                .address(requestDto.getAddress())
                .description(requestDto.getDescription())
                .active(Boolean.TRUE.equals(requestDto.getActive()))
                .build();
        supplierRepository.save(supplier);
        return AddSupplierResponseDto.builder()
                .id(supplier.getId()).build();

    }

    @Override
    public UpdateSupplierResponseDto updateSupplier(UpdateSupplierRequestDto requestDto) {
        Supplier supplier = supplierRepository.findByIdForUpdate(requestDto.getId()).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR));//SupplierError.SUPPLIER_NOT_FOUND));
        supplierRepository.findByCode(requestDto.getCode()).filter(s -> !s.getId().equals(supplier.getId())).ifPresent(s -> {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);//SupplierError.SUPPLIER_CODE_ALREADY_EXISTS);
        });
        supplier.setCode(requestDto.getCode());
        supplier.setName(requestDto.getName());
        supplier.setContactPerson(requestDto.getContactPerson());
        supplier.setPhoneNumber(requestDto.getPhoneNumber());
        supplier.setEmail(requestDto.getEmail());
        supplier.setAddress(requestDto.getAddress());
        supplier.setDescription(requestDto.getDescription());
        supplier.setActive(requestDto.getActive());
        supplierRepository.save(supplier);
        return UpdateSupplierResponseDto.builder()
                .id(supplier.getId())
                .build();

    }

    @Override
    public ChangeStatusSupplierResponseDto changeSupplierStatus(ChangeStatusSupplierRequestDto requestDto) {
        Supplier supplier = supplierRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));//SupplierError.SUPPLIER_NOT_FOUND));
        supplier.setActive(!supplier.getActive());
        supplierRepository.save(supplier);
        return new ChangeStatusSupplierResponseDto();
    }
}
