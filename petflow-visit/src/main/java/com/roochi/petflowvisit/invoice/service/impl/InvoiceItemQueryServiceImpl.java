package com.roochi.petflowvisit.invoice.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.cmmon.InvoiceDto;
import com.roochi.petflowvisit.dto.cmmon.InvoiceItemDto;
import com.roochi.petflowvisit.dto.request.invoice.GetInvoiceItemByIdRequestDto;
import com.roochi.petflowvisit.dto.request.invoice.GetInvoiceItemForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.invoice.SearchInvoiceItemRequestDto;
import com.roochi.petflowvisit.dto.response.invoice.InvoiceItemResponseDto;
import com.roochi.petflowvisit.dto.response.invoice.SearchInvoiceItemResponseDto;
import com.roochi.petflowvisit.dto.response.invoice.SearchInvoiceResponseDto;
import com.roochi.petflowvisit.invoice.entity.Invoice;
import com.roochi.petflowvisit.invoice.entity.InvoiceItem;
import com.roochi.petflowvisit.invoice.mapper.InvoiceItemMapper;
import com.roochi.petflowvisit.invoice.repository.InvoiceItemRepository;
import com.roochi.petflowvisit.invoice.service.query.InvoiceItemQueryService;
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
 * @created 7/25/2026
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class InvoiceItemQueryServiceImpl implements InvoiceItemQueryService {
    private final InvoiceItemRepository invoiceItemRepository;
    private final InvoiceItemMapper invoiceItemMapper;

    @Override
    public InvoiceItemResponseDto getInvoiceItemById(GetInvoiceItemByIdRequestDto requestDto) {
        InvoiceItem entity = invoiceItemRepository.findById(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));
                                //InvoiceItemError.INVOICE_ITEM_NOT_FOUND));

        return invoiceItemMapper.toResponseDto(entity);
    }

    @Override
    public InvoiceItemResponseDto getInvoiceItemForUpdate(GetInvoiceItemForUpdateRequestDto requestDto) {
        InvoiceItem entity = invoiceItemRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));
        //InvoiceItemError.INVOICE_ITEM_NOT_FOUND));

        return invoiceItemMapper.toResponseDto(entity);
    }

    @Override
    public SearchInvoiceItemResponseDto searchInvoiceItem(SearchInvoiceItemRequestDto requestDto) {

        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<InvoiceItem> page = invoiceItemRepository.search(
                requestDto.getInvoiceId(),
                requestDto.getItemType(),
                requestDto.getReferenceId()
                ,pageRequest);
        List<InvoiceItemDto> invoiceItems =
                page.getContent()
                        .stream()
                        .map(invoiceItemMapper::toInvoiceItemDto).toList();

        SearchInvoiceItemResponseDto response = new SearchInvoiceItemResponseDto();
        response.setResults(invoiceItems);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
