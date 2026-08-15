package com.roochi.petflowdashboard.service.impl;

import com.roochi.petflowdashboard.dto.response.DailyPurchaseReportDto;
import com.roochi.petflowdashboard.dto.response.PurchaseDashboardDto;
import com.roochi.petflowdashboard.dto.response.TopPurchasedDrugDto;
import com.roochi.petflowdashboard.repository.PurchaseDashboardRepository;
import com.roochi.petflowdashboard.repository.PurchaseItemDashboardRepository;
import com.roochi.petflowdashboard.service.PurchaseDashboardQueryService;
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
public class PurchaseDashboardQueryServiceImpl implements PurchaseDashboardQueryService {

    private final PurchaseDashboardRepository purchaseDashboardRepository;
    private final PurchaseItemDashboardRepository purchaseItemDashboardRepository;

    @Override
    public PurchaseDashboardDto getSummary(
            Long clinicId,
            LocalDate from,
            LocalDate to
    ) {

        long count =
                purchaseDashboardRepository.countPurchases(
                        clinicId,
                        from,
                        to
                );

        BigDecimal totalAmount =
                purchaseDashboardRepository.sumPurchaseAmount(
                        clinicId,
                        from,
                        to
                );

        return PurchaseDashboardDto.builder()
                .count(count)
                .totalAmount(totalAmount)
                .build();
    }

    @Override
    public List<DailyPurchaseReportDto> getDailyReport(
            Long clinicId,
            LocalDate from,
            LocalDate to
    ) {

        return purchaseDashboardRepository
                .getDailyPurchases(
                        clinicId,
                        from,
                        to
                )
                .stream()
                .map(row -> DailyPurchaseReportDto.builder()
                        .date((LocalDate) row[0])
                        .purchaseCount(
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
    public List<TopPurchasedDrugDto> getTopPurchasedDrugs(
            Long clinicId,
            LocalDate from,
            LocalDate to,
            int limit
    ) {

        return purchaseItemDashboardRepository
                .findTopPurchasedDrugs(
                        clinicId,
                        from,
                        to
                )
                .stream()
                .limit(limit)
                .map(row ->
                        TopPurchasedDrugDto.builder()
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