package com.roochi.petflowticket.dto.response;

import com.roochi.petflowticket.entity.enums.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {

    private Long id;

    private String ticketNumber;

    private String title;

    private String description;

    private TicketCategory category;

    private TicketPriority priority;

    private TicketStatus status;

    private TicketModule module;

    private Long referenceId;

    private Long createdByUserId;

    private Long assignedToUserId;

    private LocalDateTime createdAt;

    private LocalDateTime resolvedAt;

    private LocalDateTime closedAt;

    private String resolvedInVersion;

    private String resolution;
}
