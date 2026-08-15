package com.roochi.petflowticket.dto.request;

import com.roochi.petflowticket.entity.enums.TicketStatus;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketStatusFilterRequestDto {

    private TicketStatus status;

    private Long assignedToUserId;
}
