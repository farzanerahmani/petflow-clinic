package com.roochi.petflowinventory.purchase.service.impl;

import com.roochi.petflowinventory.purchase.dto.purchase.request.GetPurchaseByIdRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchase.request.SearchPurchaseRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.PurchaseResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.PurchaseSummaryDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.SearchPurchaseResponseDto;
import com.roochi.petflowinventory.purchase.entity.Purchase;
import com.roochi.petflowinventory.purchase.mapper.PurchaseMapper;
import com.roochi.petflowinventory.purchase.repository.PurchaseRepository;
import com.roochi.petflowinventory.purchase.service.query.PurchaseQueryService;
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
public class PurchaseQueryServiceImpl implements PurchaseQueryService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;

    @Override
    public PurchaseResponseDto getPurchaseById(GetPurchaseByIdRequestDto requestDto) {
        Purchase purchase = purchaseRepository.findById(requestDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR));
        //PurchaseError.PURCHASE_NOT_FOUND));
        return purchaseMapper.toResponseDto(purchase);
    }

    @Override
    public SearchPurchaseResponseDto searchPurchase(SearchPurchaseRequestDto requestDto) {
        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));


        Page<Purchase> page = purchaseRepository.search(
                requestDto.getPurchaseNumber(),
                requestDto.getSupplierId(),
                requestDto.getStatus(),
                requestDto.getFromDate(),
                requestDto.getToDate(),
                pageRequest);

        List<PurchaseSummaryDto> purchases =
                page.getContent()
                        .stream()
                        .map(purchaseMapper::toSummaryDto)
                        .toList();

        SearchPurchaseResponseDto response = new SearchPurchaseResponseDto();
        response.setResults(purchases);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
