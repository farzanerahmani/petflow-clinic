package com.roochi.petflowreport.service.impl;

import com.roochi.petflowinventory.purchase.entity.PurchaseItem;
import com.roochi.petflowreport.dto.request.ReportDateRangeRequestDto;
import com.roochi.petflowreport.dto.response.PurchaseReportRowDto;
import com.roochi.petflowreport.dto.response.ReportSummaryDto;
import com.roochi.petflowreport.repository.PurchaseReportRepository;
import com.roochi.petflowreport.repository.PurchaseReportSummaryRepository;
import com.roochi.petflowreport.service.PurchaseReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurchaseReportServiceImpl
        implements PurchaseReportService {

    private final PurchaseReportRepository purchaseReportRepository;

    private final PurchaseReportSummaryRepository
            purchaseReportSummaryRepository;

    @Override
    public List<PurchaseReportRowDto> getPurchaseReport(
            Long clinicId,
            ReportDateRangeRequestDto request
    ) {

        return purchaseReportRepository
                .getPurchaseReport(
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
                purchaseReportSummaryRepository.getSummary(
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

    private PurchaseReportRowDto toDto(
            PurchaseItem item
    ) {

        var purchase = item.getPurchase();

        return PurchaseReportRowDto.builder()
                .purchaseDate(
                        purchase.getPurchaseDate()
                )
                .purchaseNumber(
                        purchase.getPurchaseNumber()
                )
                .supplierName(
                        purchase.getSupplier().getName()
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