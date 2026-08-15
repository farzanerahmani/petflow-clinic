package com.roochi.petflowinventory.alert.controller;

import com.roochi.petflowinventory.alert.dto.request.ResolveAlertRequestDto;
import com.roochi.petflowinventory.alert.dto.response.AlertResponseDto;
import com.roochi.petflowinventory.alert.service.AlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@RestController
@RequestMapping("/api/inventory/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;


    @GetMapping
    public Page<AlertResponseDto> findActive(
            Pageable pageable
    ) {

        return alertService.findActive(
                pageable
        );
    }


    @GetMapping("/status/{status}")
    public Page<AlertResponseDto> findByStatus(
            @PathVariable String status,
            Pageable pageable
    ) {

        return alertService.findByStatus(
                status,
                pageable
        );
    }


    @PatchMapping("/resolve")
    public AlertResponseDto resolve(
            @Valid
            @RequestBody
            ResolveAlertRequestDto request
    ) {

        return alertService.resolve(
                request
        );
    }
}
