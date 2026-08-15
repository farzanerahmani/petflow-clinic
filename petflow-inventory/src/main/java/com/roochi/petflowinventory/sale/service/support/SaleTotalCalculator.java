package com.roochi.petflowinventory.sale.service.support;

import com.roochi.petflowinventory.sale.entity.Sale;
import com.roochi.petflowinventory.sale.repository.SaleItemRepository;
import com.roochi.petflowinventory.sale.repository.SaleRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
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
public class SaleTotalCalculator {

    private final SaleRepository saleRepository;

    private final SaleItemRepository saleItemRepository;

    public void recalculate(Long saleId) {

        Sale sale =
                saleRepository.findByIdForUpdate(saleId)
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR));

        BigDecimal total =
                saleItemRepository.calculateSaleTotal(
                        saleId
                );

        sale.setTotalAmount(total);

        saleRepository.save(sale);

    }

}
