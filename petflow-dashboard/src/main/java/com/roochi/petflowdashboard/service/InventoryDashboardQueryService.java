package com.roochi.petflowdashboard.service;

import com.roochi.petflowdashboard.dto.response.ExpiringStockDto;
import com.roochi.petflowdashboard.dto.response.InventoryDashboardDto;
import com.roochi.petflowdashboard.dto.response.LowStockDto;
import com.roochi.petflowdashboard.dto.response.OutOfStockDto;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface InventoryDashboardQueryService {

    InventoryDashboardDto getSummary(Long clinicId);

    List<LowStockDto> getLowStock(
            Long clinicId
    );

    List<OutOfStockDto> getOutOfStock(
            Long clinicId
    );

    List<ExpiringStockDto> getExpiringSoon(
            Long clinicId,
            int days
    );
}