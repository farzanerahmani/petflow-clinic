package com.roochi.petflowticket.dto.response;

import lombok.*;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDetailsResponseDto {

    private TicketResponseDto ticket;

    private List<TicketMessageResponseDto> messages;

    private List<TicketAttachmentResponseDto> attachments;
}
