package com.roochi.petflowticket.service;

import com.roochi.petflowticket.dto.request.*;
import com.roochi.petflowticket.dto.response.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
public interface TicketService {

    TicketResponseDto create(
            CreateTicketRequestDto requestDto
    );

    TicketDetailsResponseDto getById(
            Long ticketId
    );

    Page<TicketResponseDto> getMyTickets(
            Pageable pageable
    );

    Page<TicketResponseDto> getSupportTickets(
            TicketStatusFilterRequestDto requestDto,
            Pageable pageable
    );

    TicketResponseDto assign(
            AssignTicketRequestDto requestDto
    );

    TicketResponseDto changeStatus(
            ChangeTicketStatusRequestDto requestDto
    );

    TicketResponseDto close(
            CloseTicketRequestDto requestDto
    );

    TicketResponseDto reopen(
            Long ticketId
    );
}
