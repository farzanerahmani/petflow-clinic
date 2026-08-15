package com.roochi.petflowdashboard.service.impl;

import com.roochi.petflowdashboard.dto.response.DailySaleReportDto;
import com.roochi.petflowdashboard.dto.response.SaleDashboardDto;
import com.roochi.petflowdashboard.dto.response.TopSellingDrugDto;
import com.roochi.petflowdashboard.repository.SaleDashboardRepository;
import com.roochi.petflowdashboard.repository.SaleItemDashboardRepository;
import com.roochi.petflowdashboard.service.SaleDashboardQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaleDashboardQueryServiceImpl
        implements SaleDashboardQueryService {

    private final SaleDashboardRepository saleDashboardRepository;

    private final SaleItemDashboardRepository saleItemDashboardRepository;

    @Override
    public SaleDashboardDto getSummary(
            Long clinicId,
            LocalDate from,
            LocalDate to
    ) {

        long count =
                saleDashboardRepository.countSales(
                        clinicId,
                        from,
                        to
                );

        BigDecimal totalAmount =
                saleDashboardRepository.sumSalesAmount(
                        clinicId,
                        from,
                        to
                );

        return SaleDashboardDto.builder()
                .count(count)
                .totalAmount(totalAmount)
                .build();
    }

    @Override
    public List<DailySaleReportDto> getDailyReport(
            Long clinicId,
            LocalDate from,
            LocalDate to
    ) {

        return saleDashboardRepository
                .getDailySales(
                        clinicId,
                        from,
                        to
                )
                .stream()
                .map(row -> DailySaleReportDto.builder()
                        .date((LocalDate) row[0])
                        .saleCount(
                                ((Number) row[1]).longValue()
                        )
                        .totalAmount(
                                (BigDecimal) row[2]
                        )
                        .build()
                )
                .toList();
    }

    @Override
    public List<TopSellingDrugDto> getTopSellingDrugs(
            Long clinicId,
            LocalDate from,
            LocalDate to,
            int limit
    ) {

        return saleItemDashboardRepository
                .findTopSellingDrugs(
                        clinicId,
                        from,
                        to
                )
                .stream()
                .limit(limit)
                .map(row ->
                        TopSellingDrugDto.builder()
                                .drugId(
                                        ((Number) row[0])
                                                .longValue()
                                )
                                .drugName(
                                        (String) row[1]
                                )
                                .totalQuantity(
                                        (BigDecimal) row[2]
                                )
                                .totalAmount(
                                        (BigDecimal) row[3]
                                )
                                .build()
                )
                .toList();
    }
}
