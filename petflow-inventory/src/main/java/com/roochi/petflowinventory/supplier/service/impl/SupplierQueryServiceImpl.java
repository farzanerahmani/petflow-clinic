package com.roochi.petflowinventory.supplier.service.impl;

import com.roochi.petflowinventory.supplier.dto.request.GetSupplierByIdRequestDto;
import com.roochi.petflowinventory.supplier.dto.request.GetSupplierForUpdateRequestDto;
import com.roochi.petflowinventory.supplier.dto.request.SearchSupplierRequestDto;
import com.roochi.petflowinventory.supplier.dto.response.SearchSupplierResponseDto;
import com.roochi.petflowinventory.supplier.dto.response.SupplierResponseDto;
import com.roochi.petflowinventory.supplier.dto.response.SupplierSummaryDto;
import com.roochi.petflowinventory.supplier.entity.Supplier;
import com.roochi.petflowinventory.supplier.mapper.SupplierMapper;
import com.roochi.petflowinventory.supplier.repository.SupplierRepository;
import com.roochi.petflowinventory.supplier.service.query.SupplierQueryService;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SupplierQueryServiceImpl implements SupplierQueryService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public SupplierResponseDto getSupplierById(GetSupplierByIdRequestDto requestDto) {
        Supplier supplier = supplierRepository.findById(requestDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));//SupplierError.SUPPLIER_NOT_FOUND));
        return supplierMapper.toResponseDto(supplier);
    }

    @Override
    public SupplierResponseDto getSupplierForUpdate(GetSupplierForUpdateRequestDto requestDto) {
        Supplier supplier = supplierRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));//SupplierError.SUPPLIER_NOT_FOUND));
        return supplierMapper.toResponseDto(supplier);
    }

    @Override
    public SearchSupplierResponseDto searchSupplier(SearchSupplierRequestDto requestDto) {
        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<Supplier> page = supplierRepository.
                search(requestDto.getCode(),
                        requestDto.getName(),
                        requestDto.getActive()
                        , pageRequest);

        List<SupplierSummaryDto> suppliers =
                page.getContent()
                        .stream()
                        .map(supplierMapper::toSummaryDto)
                        .toList();

        SearchSupplierResponseDto response = new SearchSupplierResponseDto();
        response.setResults(suppliers);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
