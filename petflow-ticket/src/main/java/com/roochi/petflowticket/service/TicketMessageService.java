package com.roochi.petflowticket.service;

import com.roochi.petflowticket.dto.request.AddTicketMessageRequestDto;
import com.roochi.petflowticket.dto.response.TicketMessageResponseDto;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
public interface TicketMessageService {

    TicketMessageResponseDto addClinicUserMessage(AddTicketMessageRequestDto requestDto);

    TicketMessageResponseDto addSupportMessage(AddTicketMessageRequestDto requestDto);

    List<TicketMessageResponseDto> getClinicMessages(Long ticketId);

    List<TicketMessageResponseDto> getSupportMessages(Long ticketId);
}
