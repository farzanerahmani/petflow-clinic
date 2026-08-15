package com.roochi.petflowaudit.dto.request;

import com.roochi.petflowaudit.entity.enums.AuditAction;
import com.roochi.petflowaudit.entity.enums.AuditEntityType;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuditLogRequestDto {

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
}
