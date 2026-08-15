package com.roochi.petflowaudit.dto.request;

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
public class AuditSnapshotDto {

    private String oldValue;

    private String newValue;
}
