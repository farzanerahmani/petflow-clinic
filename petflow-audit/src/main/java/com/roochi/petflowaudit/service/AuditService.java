package com.roochi.petflowaudit.service;

import com.roochi.petflowaudit.dto.request.CreateAuditLogRequestDto;
import com.roochi.petflowaudit.dto.request.AuditSearchRequestDto;
import com.roochi.petflowaudit.dto.response.AuditLogResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */
public interface AuditService {

    void log(
            CreateAuditLogRequestDto request
    );

    Page<AuditLogResponseDto> search(
            Long clinicId,
            AuditSearchRequestDto request,
            Pageable pageable
    );

    AuditLogResponseDto findById(
            Long clinicId,
            Long id
    );

    void prepareOldValue(
            Object oldValue
    );
}