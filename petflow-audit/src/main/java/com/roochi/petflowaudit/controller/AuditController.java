package com.roochi.petflowaudit.controller;

import com.roochi.petflowaudit.dto.response.AuditLogResponseDto;
import com.roochi.petflowaudit.service.AuditService;
import com.roochi.petflowshared.security.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */
import com.roochi.petflowaudit.dto.request.AuditSearchRequestDto;


@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('CLINIC_ADMIN', 'SUPER_ADMIN')")
public class AuditController {

    private final AuditService auditService;


    @GetMapping
    public ResponseEntity<Page<AuditLogResponseDto>>
    search(
            @ModelAttribute
            AuditSearchRequestDto request,
            Pageable pageable) {

        JwtAuthentication authentication =
                (JwtAuthentication)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication();

        Long clinicId =
                authentication.getClinicId();

        return ResponseEntity.ok(
                auditService.search(
                        clinicId,
                        request,
                        pageable
                )
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<AuditLogResponseDto>
    findById(
            @PathVariable Long id) {

        JwtAuthentication authentication =
                (JwtAuthentication)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication();

        Long clinicId =
                authentication.getClinicId();

        return ResponseEntity.ok(
                auditService.findById(
                        clinicId,
                        id
                )
        );
    }
}
