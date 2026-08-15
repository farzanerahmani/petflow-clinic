package com.roochi.petflowticket.service.impl;

import com.roochi.petflowshared.security.JwtAuthentication;
import com.roochi.petflowticket.dto.request.*;
import com.roochi.petflowticket.dto.response.*;
import com.roochi.petflowticket.entity.Ticket;
import com.roochi.petflowticket.entity.enums.TicketStatus;
import com.roochi.petflowticket.repository.TicketRepository;
import com.roochi.petflowticket.service.TicketMessageService;
import com.roochi.petflowticket.service.TicketService;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */


@Service
@RequiredArgsConstructor
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final TicketMessageService ticketMessageService;

    @Override
    public TicketResponseDto create(
            CreateTicketRequestDto requestDto) {

        var authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        var jwtAuthentication =
                (JwtAuthentication) authentication;

        Long clinicId =
                jwtAuthentication.getClinicId();

        Long userId =
                jwtAuthentication.getUserId();

        String ticketNumber =
                generateTicketNumber();

        Ticket ticket =
                Ticket.builder()
                        .ticketNumber(ticketNumber)
                        .title(requestDto.getTitle())
                        .description(requestDto.getDescription())
                        .category(requestDto.getCategory())
                        .priority(requestDto.getPriority())
                        .module(requestDto.getModule())
                        .referenceId(requestDto.getReferenceId())
                        .status(TicketStatus.OPEN)
                        .createdByUserId(userId)
                        .build();

        /*
         * clinicId از Authentication می‌آید.
         *
         * اگر ClinicSoftDeleteEntity دارای setter برای clinicId
         * باشد، این مقدار را روی entity قرار می‌دهیم.
         */
        ticket.setClinicId(clinicId);

        ticketRepository.save(ticket);

        return toResponse(ticket);
    }

    @Override
    @Transactional(readOnly = true)
    public TicketDetailsResponseDto getById(
            Long ticketId) {

        var authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        var jwtAuthentication =
                (JwtAuthentication) authentication;

        Long clinicId =
                jwtAuthentication.getClinicId();

        Ticket ticket =
                ticketRepository
                        .findByIdAndClinicIdAndDeletedFalse(
                                ticketId,
                                clinicId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        return TicketDetailsResponseDto.builder()
                .ticket(toResponse(ticket))
                .messages(
                        ticketMessageService.getClinicMessages(ticketId)
                )
                .attachments(
                        java.util.Collections.emptyList()
                )
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TicketResponseDto> getMyTickets(
            Pageable pageable) {

        var authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        var jwtAuthentication =
                (JwtAuthentication) authentication;

        Long clinicId =
                jwtAuthentication.getClinicId();

        return ticketRepository
                .findAllByClinicIdAndDeletedFalse(
                        clinicId,
                        pageable
                )
                .map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TicketResponseDto> getSupportTickets(
            TicketStatusFilterRequestDto requestDto,
            Pageable pageable) {

        return ticketRepository
                .searchSupportTickets(
                        requestDto.getStatus(),
                        requestDto.getAssignedToUserId(),
                        pageable
                )
                .map(this::toResponse);
    }

    @Override
    public TicketResponseDto assign(
            AssignTicketRequestDto requestDto) {

        Ticket ticket =
                ticketRepository.findById(
                                requestDto.getTicketId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        ticket.setAssignedToUserId(
                requestDto.getSupportUserId()
        );

        if (ticket.getStatus() == TicketStatus.OPEN) {

            ticket.setStatus(
                    TicketStatus.IN_PROGRESS
            );
        }

        return toResponse(ticket);
    }

    @Override
    public TicketResponseDto changeStatus(
            ChangeTicketStatusRequestDto requestDto) {

        Ticket ticket =
                ticketRepository.findById(
                                requestDto.getTicketId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        TicketStatus currentStatus =
                ticket.getStatus();

        TicketStatus newStatus =
                requestDto.getStatus();

        validateStatusTransition(
                currentStatus,
                newStatus
        );

        ticket.setStatus(newStatus);

        if (newStatus == TicketStatus.RESOLVED) {

            ticket.setResolvedAt(
                    java.time.LocalDateTime.now()
            );
        }

        return toResponse(ticket);
    }

    @Override
    public TicketResponseDto close(
            CloseTicketRequestDto requestDto) {

        Ticket ticket =
                ticketRepository.findById(
                                requestDto.getTicketId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        if (ticket.getStatus()
                != TicketStatus.RESOLVED) {

            throw new IllegalStateException(
                    "Only resolved tickets can be closed"
            );
        }

        ticket.setResolution(
                requestDto.getResolution()
        );

        ticket.setResolvedInVersion(
                requestDto.getResolvedInVersion()
        );

        ticket.setClosedAt(
                java.time.LocalDateTime.now()
        );

        ticket.setStatus(
                TicketStatus.CLOSED
        );

        return toResponse(ticket);
    }

    @Override
    public TicketResponseDto reopen(
            Long ticketId) {

        var authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        var jwtAuthentication =
                (JwtAuthentication) authentication;

        Long clinicId =
                jwtAuthentication.getClinicId();

        Ticket ticket =
                ticketRepository
                        .findByIdAndClinicIdAndDeletedFalse(
                                ticketId,
                                clinicId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        if (ticket.getStatus()
                != TicketStatus.CLOSED) {

            throw new IllegalStateException(
                    "Only closed tickets can be reopened"
            );
        }

        ticket.setStatus(
                TicketStatus.REOPENED
        );

        ticket.setClosedAt(null);

        return toResponse(ticket);
    }

    private void validateStatusTransition(
            TicketStatus current,
            TicketStatus next) {

        if (current == next) {
            return;
        }

        boolean valid =
                switch (current) {

                    case OPEN ->
                            next == TicketStatus.IN_PROGRESS;

                    case IN_PROGRESS ->
                            next == TicketStatus.WAITING_FOR_USER
                                    || next == TicketStatus.RESOLVED;

                    case WAITING_FOR_USER ->
                            next == TicketStatus.IN_PROGRESS;

                    case RESOLVED ->
                            next == TicketStatus.CLOSED;

                    case CLOSED ->
                            next == TicketStatus.REOPENED;

                    case REOPENED ->
                            next == TicketStatus.IN_PROGRESS;
                };

        if (!valid) {

            throw new IllegalStateException(
                    "Invalid ticket status transition: "
                            + current
                            + " -> "
                            + next
            );
        }
    }

    private String generateTicketNumber() {

        /*
         * فعلاً ساده.
         *
         * در نسخه production بهتر است Ticket Number
         * با Sequence/Generator مطمئن ساخته شود.
         */
        return "TCK-" +
                System.currentTimeMillis();
    }

    private TicketResponseDto toResponse(
            Ticket ticket) {

        return TicketResponseDto.builder()
                .id(ticket.getId())
                .ticketNumber(ticket.getTicketNumber())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .category(ticket.getCategory())
                .priority(ticket.getPriority())
                .status(ticket.getStatus())
                .module(ticket.getModule())
                .referenceId(ticket.getReferenceId())
                .createdByUserId(ticket.getCreatedByUserId())
                .assignedToUserId(ticket.getAssignedToUserId())
                .createdAt(ticket.getCreatedAt())
                .resolvedAt(ticket.getResolvedAt())
                .closedAt(ticket.getClosedAt())
                .resolvedInVersion(
                        ticket.getResolvedInVersion()
                )
                .resolution(ticket.getResolution())
                .build();
    }
}
