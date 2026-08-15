package com.roochi.petflowinventory.expiration.controller;

import com.roochi.petflowinventory.expiration.dto.response.ExpirationStockResponseDto;
import com.roochi.petflowinventory.expiration.service.ExpirationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
@RestController
@RequestMapping("/api/inventory/expiration")
@RequiredArgsConstructor
public class ExpirationController {


    private final ExpirationService expirationService;



    @GetMapping("/expired")
    public List<ExpirationStockResponseDto> expired(){

        return expirationService.findExpired();
    }



    @GetMapping("/near")
    public List<ExpirationStockResponseDto> near(
            @RequestParam(defaultValue = "30")
            int days
    ){

        return expirationService.findNearExpiration(days);
    }
}