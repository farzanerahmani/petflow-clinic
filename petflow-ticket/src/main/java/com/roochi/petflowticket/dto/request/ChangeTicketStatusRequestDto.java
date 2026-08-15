package com.roochi.petflowticket.dto.request;

import com.roochi.petflowticket.entity.enums.TicketStatus;
import jakarta.validation.constraints.NotNull;
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
public class ChangeTicketStatusRequestDto {

    @NotNull
    private Long ticketId;

    @NotNull
    private TicketStatus status;
}
