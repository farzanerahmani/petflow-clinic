package com.roochi.petflowinventory.expiration.service;

import com.roochi.petflowinventory.expiration.dto.response.ExpirationStockResponseDto;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */


public interface ExpirationService {


    List<ExpirationStockResponseDto> findExpired();


    List<ExpirationStockResponseDto> findNearExpiration(
            int days
    );
}
