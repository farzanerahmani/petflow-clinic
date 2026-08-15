package com.roochi.petflowreport.service.impl;

import com.roochi.petflowinventory.sale.entity.SaleItem;
import com.roochi.petflowreport.dto.request.ReportDateRangeRequestDto;
import com.roochi.petflowreport.dto.response.ReportSummaryDto;
import com.roochi.petflowreport.dto.response.SaleReportRowDto;
import com.roochi.petflowreport.repository.SaleReportRepository;
import com.roochi.petflowreport.repository.SaleReportSummaryRepository;
import com.roochi.petflowreport.service.SaleReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaleReportServiceImpl
        implements SaleReportService {

    private final SaleReportRepository saleReportRepository;

    private final SaleReportSummaryRepository
            saleReportSummaryRepository;

    @Override
    public List<SaleReportRowDto> getSalesReport(
            Long clinicId,
            ReportDateRangeRequestDto request
    ) {

        return saleReportRepository
                .getSalesReport(
                        clinicId,
                        request != null
                                ? request.getFrom()
                                : null,
                        request != null
                                ? request.getTo()
                                : null,
                        request != null
                                ? request.getWarehouseId()
                                : null,
                        request != null
                                ? request.getDrugId()
                                : null
                )
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public ReportSummaryDto getSummary(
            Long clinicId,
            ReportDateRangeRequestDto request
    ) {

        Object[] result =
                saleReportSummaryRepository.getSummary(
                        clinicId,
                        request != null
                                ? request.getFrom()
                                : null,
                        request != null
                                ? request.getTo()
                                : null,
                        request != null
                                ? request.getWarehouseId()
                                : null,
                        request != null
                                ? request.getDrugId()
                                : null
                );

        return buildSummary(result);
    }

    private ReportSummaryDto buildSummary(
            Object[] result
    ) {

        Long count =
                result[0] == null
                        ? 0L
                        : ((Number) result[0]).longValue();

        BigDecimal totalQuantity =
                toBigDecimal(result[1]);

        BigDecimal totalAmount =
                toBigDecimal(result[2]);

        BigDecimal averageAmount =
                count > 0
                        ? totalAmount.divide(
                        BigDecimal.valueOf(count),
                        2,
                        RoundingMode.HALF_UP
                )
                        : BigDecimal.ZERO;

        return ReportSummaryDto.builder()
                .totalRecords(count)
                .totalQuantity(totalQuantity)
                .totalAmount(totalAmount)
                .averageAmount(averageAmount)
                .build();
    }

    private BigDecimal toBigDecimal(
            Object value
    ) {

        if (value == null) {
            return BigDecimal.ZERO;
        }

        if (value instanceof BigDecimal decimal) {
            return decimal;
        }

        return new BigDecimal(
                value.toString()
        );
    }

    private SaleReportRowDto toDto(
            SaleItem item
    ) {

        var sale = item.getSale();

        return SaleReportRowDto.builder()
                .saleNumber(
                        sale.getSaleNumber()
                )
                .saleDate(
                        sale.getSaleDate()
                )
                .drugName(
                        item.getDrug().getBrandName()
                )
                .quantity(
                        item.getQuantity()
                )
                .unitPrice(
                        item.getUnitPrice()
                )
                .lineTotal(
                        item.getLineTotal()
                )
                .build();
    }
}