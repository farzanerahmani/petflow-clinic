package com.roochi.petflowaudit.mapper;

import com.roochi.petflowaudit.dto.response.AuditLogResponseDto;
import com.roochi.petflowaudit.entity.AuditLog;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */
@Component
public class AuditLogMapper {

    public AuditLogResponseDto toDto(
            AuditLog entity) {

        return AuditLogResponseDto.builder()
                .id(entity.getId())
                .clinicId(entity.getClinicId())
                .userId(entity.getUserId())
                .action(entity.getAction())
                .entityType(entity.getEntityType())
                .entityId(entity.getEntityId())
                .description(entity.getDescription())
                .oldValue(entity.getOldValue())
                .newValue(entity.getNewValue())
                .ipAddress(entity.getIpAddress())
                .userAgent(entity.getUserAgent())
                .eventAt(entity.getEventAt())
                .build();
    }
}
