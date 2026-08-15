package com.roochi.petflowaudit.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roochi.petflowaudit.aop.AuditContextHolder;
import com.roochi.petflowaudit.aop.AuditSnapshotService;
import com.roochi.petflowaudit.dto.request.AuditSearchRequestDto;
import com.roochi.petflowaudit.dto.request.CreateAuditLogRequestDto;
import com.roochi.petflowaudit.dto.response.AuditLogResponseDto;
import com.roochi.petflowaudit.entity.AuditLog;
import com.roochi.petflowaudit.mapper.AuditLogMapper;
import com.roochi.petflowaudit.repository.AuditLogRepository;
import com.roochi.petflowaudit.service.AuditService;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */
@Service
@RequiredArgsConstructor
public class AuditServiceImpl
        implements AuditService {

    private final AuditLogRepository auditLogRepository;

    private final AuditLogMapper auditLogMapper;

    private final ObjectMapper objectMapper;

    private final AuditSnapshotService snapshotService;

    @Override
    @Transactional
    public void log(
            CreateAuditLogRequestDto request) {

        AuditLog auditLog =
                AuditLog.builder()
                        .clinicId(
                                request.getClinicId()
                        )
                        .userId(
                                request.getUserId()
                        )
                        .action(
                                request.getAction()
                        )
                        .entityType(
                                request.getEntityType()
                        )
                        .entityId(
                                request.getEntityId()
                        )
                        .description(
                                request.getDescription()
                        )
                        .oldValue(
                                normalizeJson(
                                        request.getOldValue()
                                )
                        )
                        .newValue(
                                normalizeJson(
                                        request.getNewValue()
                                )
                        )
                        .ipAddress(
                                request.getIpAddress()
                        )
                        .userAgent(
                                request.getUserAgent()
                        )
                        .eventAt(
                                LocalDateTime.now()
                        )
                        .build();

        auditLogRepository.save(
                auditLog
        );
    }





    @Transactional(readOnly = true)
    public Page<AuditLogResponseDto> search(
            Long clinicId,
            AuditSearchRequestDto request,
            Pageable pageable) {

        Page<AuditLog> result =
                auditLogRepository.search(
                        clinicId,
                        request.getUserId(),
                        request.getAction(),
                        request.getEntityType(),
                        request.getEntityId(),
                        request.getFrom(),
                        request.getTo(),
                        pageable
                );

        return result.map(
                auditLogMapper::toDto
        );
    }

    @Override
    @Transactional(readOnly = true)
    public AuditLogResponseDto findById(
            Long clinicId,
            Long id) {

        AuditLog auditLog =
                auditLogRepository
                        .findByIdAndClinicId(
                                id,
                                clinicId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        return auditLogMapper.toDto(
                auditLog
        );
    }

    @Override
    public void prepareOldValue(
            Object oldValue
    ) {

        String snapshot =
                oldValue == null
                        ? null
                        : snapshotService.snapshot(
                        oldValue
                );

        AuditContextHolder.setOldValue(
                snapshot
        );
    }

    private String normalizeJson(
            String value) {

        if (value == null) {
            return null;
        }

        try {

            Object json =
                    objectMapper.readValue(
                            value,
                            Object.class
                    );

            return objectMapper.writeValueAsString(
                    json
            );

        } catch (JsonProcessingException e) {

            return value;
        }
    }
}
