package com.roochi.petflowticket.dto.response;

import com.roochi.petflowticket.entity.enums.TicketMessageType;
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
public class TicketMessageResponseDto {

    private Long id;

    private Long senderUserId;

    private TicketMessageType type;

    private String message;

    private LocalDateTime createdAt;
}
