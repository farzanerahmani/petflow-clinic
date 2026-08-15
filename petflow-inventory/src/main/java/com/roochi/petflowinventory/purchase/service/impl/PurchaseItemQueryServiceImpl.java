package com.roochi.petflowinventory.purchase.service.impl;

import com.roochi.petflowinventory.purchase.dto.purchase.response.PurchaseSummaryDto;
import com.roochi.petflowinventory.purchase.dto.purchase.response.SearchPurchaseResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchaseitem.request.GetPurchaseItemByIdRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchaseitem.request.SearchPurchaseItemRequestDto;
import com.roochi.petflowinventory.purchase.dto.purchaseitem.response.PurchaseItemResponseDto;
import com.roochi.petflowinventory.purchase.dto.purchaseitem.response.PurchaseItemSummaryDto;
import com.roochi.petflowinventory.purchase.dto.purchaseitem.response.SearchPurchaseItemResponseDto;
import com.roochi.petflowinventory.purchase.entity.Purchase;
import com.roochi.petflowinventory.purchase.entity.PurchaseItem;
import com.roochi.petflowinventory.purchase.mapper.PurchaseItemMapper;
import com.roochi.petflowinventory.purchase.repository.PurchaseItemRepository;
import com.roochi.petflowinventory.purchase.service.query.PurchaseItemQueryService;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.nodes.NodeTuple;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurchaseItemQueryServiceImpl implements PurchaseItemQueryService {
    private final PurchaseItemMapper purchaseItemMapper;
    private final PurchaseItemRepository purchaseItemRepository;

    @Override
    public PurchaseItemResponseDto getPurchaseItemById(GetPurchaseItemByIdRequestDto requestDto) {
        PurchaseItem item = purchaseItemRepository.findById(requestDto.getId()).
                orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));//PurchaseItemError.PURCHASE_ITEM_NOT_FOUND));
        return purchaseItemMapper.toResponseDto(item);
    }

    @Override
    public SearchPurchaseItemResponseDto searchPurchaseItem(SearchPurchaseItemRequestDto requestDto) {
        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<PurchaseItem> page = purchaseItemRepository.search(
                requestDto.getPurchaseId(),
                requestDto.getDrugId(),
                pageRequest);


        List<PurchaseItemSummaryDto> purchaseItems =
                page.getContent()
                        .stream()
                        .map(purchaseItemMapper::toSummaryDto)
                        .toList();

        SearchPurchaseItemResponseDto response = new SearchPurchaseItemResponseDto();
        response.setResults(purchaseItems);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
