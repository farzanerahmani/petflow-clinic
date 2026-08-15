package com.roochi.petflowinventory.alert.service;

import com.roochi.petflowinventory.alert.dto.request.ResolveAlertRequestDto;
import com.roochi.petflowinventory.alert.dto.response.AlertResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


public interface AlertService {

    Page<AlertResponseDto> findActive(
            Pageable pageable
    );

    Page<AlertResponseDto> findByStatus(
            String status,
            Pageable pageable
    );

    AlertResponseDto resolve(
            ResolveAlertRequestDto request
    );

    void checkStock(
            Long stockId
    );
}
