package com.roochi.petflowinventory.sale.service.impl;

import com.roochi.petflowinventory.sale.dto.request.AddSaleItemRequestDto;
import com.roochi.petflowinventory.sale.dto.request.DeleteSaleItemRequestDto;
import com.roochi.petflowinventory.sale.dto.request.UpdateSaleItemRequestDto;
import com.roochi.petflowinventory.sale.dto.response.AddSaleItemResponseDto;
import com.roochi.petflowinventory.sale.dto.response.DeleteSaleItemResponseDto;
import com.roochi.petflowinventory.sale.dto.response.UpdateSaleItemResponseDto;
import com.roochi.petflowinventory.sale.entity.Sale;
import com.roochi.petflowinventory.sale.entity.SaleItem;
import com.roochi.petflowinventory.sale.entity.enums.SaleStatus;
import com.roochi.petflowinventory.sale.repository.SaleItemRepository;
import com.roochi.petflowinventory.sale.repository.SaleRepository;
import com.roochi.petflowinventory.sale.service.command.SaleItemCommandService;
import com.roochi.petflowinventory.sale.service.support.SaleTotalCalculator;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.drug.repository.DrugRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class SaleItemCommandServiceImpl implements SaleItemCommandService {

    private final SaleRepository saleRepository;

    private final SaleItemRepository saleItemRepository;

    private final DrugRepository drugRepository;

    private final SaleTotalCalculator saleTotalCalculator;

    @Override
    public AddSaleItemResponseDto addSaleItem(
            AddSaleItemRequestDto requestDto) {

        Sale sale =
                saleRepository.findByIdForUpdate(
                                requestDto.getSaleId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR));

        if (sale.getStatus() != SaleStatus.DRAFT) {

            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
        }

        Drug drug =
                drugRepository.findById(
                                requestDto.getDrugId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR));

        BigDecimal lineTotal =
                requestDto.getQuantity()
                        .multiply(requestDto.getUnitPrice());

        SaleItem item =
                SaleItem.builder()
                        .sale(sale)
                        .drug(drug)
                        .quantity(requestDto.getQuantity())
                        .unitPrice(requestDto.getUnitPrice())
                        .lineTotal(lineTotal)
                        .build();

        saleItemRepository.save(item);

        saleTotalCalculator.recalculate(
                sale.getId());

        return AddSaleItemResponseDto.builder()
                .id(item.getId())
                .build();
    }

    @Override
    public UpdateSaleItemResponseDto updateSaleItem(
            UpdateSaleItemRequestDto requestDto) {

        SaleItem item =
                saleItemRepository.findByIdForUpdate(requestDto.getId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR));

        if (item.getSale().getStatus() != SaleStatus.DRAFT) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
        }

        Drug drug =
                drugRepository.findById(requestDto.getDrugId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR));

        item.setDrug(drug);
        item.setQuantity(requestDto.getQuantity());
        item.setUnitPrice(requestDto.getUnitPrice());
        item.setLineTotal(
                requestDto.getQuantity()
                        .multiply(requestDto.getUnitPrice())
        );

        saleItemRepository.save(item);

        saleTotalCalculator.recalculate(item.getSale().getId());

        return UpdateSaleItemResponseDto.builder()
                .id(item.getId())
                .build();
    }

    @Override
    public DeleteSaleItemResponseDto deleteSaleItem(
            DeleteSaleItemRequestDto requestDto) {

        SaleItem item =
                saleItemRepository.findByIdForUpdate(requestDto.getId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR));

        if (item.getSale().getStatus() != SaleStatus.DRAFT) {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR);
        }

        Long saleId = item.getSale().getId();

        saleItemRepository.delete(item);

        saleTotalCalculator.recalculate(saleId);

        return DeleteSaleItemResponseDto.builder()
                .id(item.getId())
                .message("Sale item deleted successfully.")
                .build();
    }
}