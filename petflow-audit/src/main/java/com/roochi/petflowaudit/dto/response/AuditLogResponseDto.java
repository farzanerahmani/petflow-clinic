package com.roochi.petflowaudit.dto.response;

import com.roochi.petflowaudit.entity.enums.AuditAction;
import com.roochi.petflowaudit.entity.enums.AuditEntityType;
import lombok.*;

import java.time.LocalDateTime;
/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogResponseDto {

    private Long id;

    private Long clinicId;

    private Long userId;

    private AuditAction action;

    private AuditEntityType entityType;

    private Long entityId;

    private String description;

    private String oldValue;

    private String newValue;

    private String ipAddress;

    private String userAgent;

    private LocalDateTime eventAt;
}
