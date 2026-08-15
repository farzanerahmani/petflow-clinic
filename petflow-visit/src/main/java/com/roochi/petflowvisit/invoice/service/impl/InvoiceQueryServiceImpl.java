package com.roochi.petflowvisit.invoice.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.drug.specification.DrugSpecification;
import com.roochi.petflowvisit.dto.cmmon.DrugDto;
import com.roochi.petflowvisit.dto.cmmon.InvoiceDto;
import com.roochi.petflowvisit.dto.request.invoice.GetInvoiceByIdRequestDto;
import com.roochi.petflowvisit.dto.request.invoice.GetInvoiceForUpdateRequestDto;
import com.roochi.petflowvisit.dto.request.invoice.SearchInvoiceRequestDto;
import com.roochi.petflowvisit.dto.response.drug.SearchDrugResponseDto;
import com.roochi.petflowvisit.dto.response.invoice.InvoiceResponseDto;
import com.roochi.petflowvisit.dto.response.invoice.SearchInvoiceResponseDto;
import com.roochi.petflowvisit.invoice.entity.Invoice;
import com.roochi.petflowvisit.invoice.mapper.InvoiceMapper;
import com.roochi.petflowvisit.invoice.repository.InvoiceRepository;
import com.roochi.petflowvisit.invoice.service.query.InvoiceQueryService;
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
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InvoiceQueryServiceImpl implements InvoiceQueryService {
    private final InvoiceRepository invoiceRepository;

    private final InvoiceMapper invoiceMapper;

    @Override
    public InvoiceResponseDto getInvoiceById(GetInvoiceByIdRequestDto requestDto) {
        Invoice invoice = invoiceRepository.findById(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));//InvoiceError.INVOICE_NOT_FOUND));

        return invoiceMapper.toResponseDto(invoice);
    }

    @Override
    public InvoiceResponseDto getInvoiceForUpdate(GetInvoiceForUpdateRequestDto requestDto) {
        Invoice invoice = invoiceRepository.findByIdForUpdate(requestDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));//InvoiceError.INVOICE_NOT_FOUND));

        return invoiceMapper.toResponseDto(invoice);
    }

    @Override
    public SearchInvoiceResponseDto searchInvoice(SearchInvoiceRequestDto requestDto) {
        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<Invoice> page = invoiceRepository.search(
                requestDto.getVisitId(),
                requestDto.getStatus(),
                requestDto.getFromDate(),
                requestDto.getToDate(),pageRequest);
        List<InvoiceDto> invoices =
                page.getContent()
                        .stream()
                        .map(invoiceMapper::toInvoiceDto).toList();

        SearchInvoiceResponseDto response = new SearchInvoiceResponseDto();
        response.setResults(invoices);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }
}
