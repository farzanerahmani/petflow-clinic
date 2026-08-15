package com.roochi.petflowaudit.dto.request;

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
public class AuditSearchRequestDto {

    private Long userId;

    private AuditAction action;

    private AuditEntityType entityType;

    private Long entityId;

    private LocalDateTime from;

    private LocalDateTime to;
}
