package com.roochi.petflowdashboard.service;

import com.roochi.petflowdashboard.dto.request.DashboardRequestDto;
import com.roochi.petflowdashboard.dto.response.DashboardResponseDto;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface DashboardQueryService {
    DashboardResponseDto getDashboard(
            Long clinicId,
            DashboardRequestDto request
    );
}
