package com.roochi.petflowticket.dto.request;

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
public class AssignTicketRequestDto {

    @NotNull
    private Long ticketId;

    @NotNull
    private Long supportUserId;
}
