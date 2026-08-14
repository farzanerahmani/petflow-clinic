package com.roochi.petflowticket.service.impl;

import com.roochi.petflowshared.security.JwtAuthentication;
import com.roochi.petflowticket.dto.request.AddTicketMessageRequestDto;
import com.roochi.petflowticket.dto.response.TicketMessageResponseDto;
import com.roochi.petflowticket.entity.Ticket;
import com.roochi.petflowticket.entity.TicketMessage;
import com.roochi.petflowticket.entity.enums.TicketMessageType;
import com.roochi.petflowticket.entity.enums.TicketStatus;
import com.roochi.petflowticket.repository.TicketMessageRepository;
import com.roochi.petflowticket.repository.TicketRepository;
import com.roochi.petflowticket.service.TicketMessageService;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TicketMessageServiceImpl implements TicketMessageService {

    private final TicketRepository ticketRepository;

    private final TicketMessageRepository ticketMessageRepository;


    // =========================================================
    // Clinic User
    // =========================================================

    @Override
    public TicketMessageResponseDto addClinicUserMessage(
            AddTicketMessageRequestDto requestDto) {

        JwtAuthentication authentication =
                getAuthentication();

        Long clinicId =
                authentication.getClinicId();

        Long userId =
                authentication.getUserId();

        Ticket ticket =
                ticketRepository
                        .findByIdAndClinicIdAndDeletedFalse(
                                requestDto.getTicketId(),
                                clinicId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        validateCanReceiveMessage(ticket);

        TicketMessage message =
                TicketMessage.builder()
                        .ticket(ticket)
                        .senderUserId(userId)
                        .type(TicketMessageType.USER_MESSAGE)
                        .message(requestDto.getMessage())
                        .build();

        ticketMessageRepository.save(message);

        /*
         * اگر Support منتظر پاسخ کاربر بوده،
         * با دریافت پیام Ticket دوباره وارد جریان بررسی می‌شود.
         */
        if (ticket.getStatus()
                == TicketStatus.WAITING_FOR_USER) {

            ticket.setStatus(
                    TicketStatus.IN_PROGRESS
            );
        }

        return toResponse(message);
    }


    // =========================================================
    // Support
    // =========================================================

    @Override
    public TicketMessageResponseDto addSupportMessage(
            AddTicketMessageRequestDto requestDto) {

        JwtAuthentication authentication =
                getAuthentication();

        Long supportUserId =
                authentication.getUserId();

        /*
         * Support به clinicId وابسته نیست.
         *
         * Ticket را فقط با id پیدا می‌کنیم.
         * دسترسی به این endpoint باید در Security
         * فقط برای Support مجاز باشد.
         */
        Ticket ticket =
                ticketRepository
                        .findByIdAndDeletedFalse(
                                requestDto.getTicketId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        validateCanReceiveMessage(ticket);

        TicketMessage message =
                TicketMessage.builder()
                        .ticket(ticket)
                        .senderUserId(supportUserId)
                        .type(TicketMessageType.SUPPORT_MESSAGE)
                        .message(requestDto.getMessage())
                        .build();

        ticketMessageRepository.save(message);

        /*
         * Support پاسخ داده است.
         *
         * اگر Ticket باز یا در حال بررسی بوده،
         * آن را در IN_PROGRESS نگه می‌داریم.
         */
        if (ticket.getStatus() == TicketStatus.OPEN
                || ticket.getStatus()
                == TicketStatus.WAITING_FOR_USER
                || ticket.getStatus()
                == TicketStatus.REOPENED) {

            ticket.setStatus(
                    TicketStatus.IN_PROGRESS
            );
        }

        return toResponse(message);
    }


    // =========================================================
    // Clinic messages
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<TicketMessageResponseDto> getClinicMessages(
            Long ticketId) {

        JwtAuthentication authentication =
                getAuthentication();

        Long clinicId =
                authentication.getClinicId();

        /*
         * اول مالکیت Ticket نسبت به Clinic را بررسی می‌کنیم.
         */
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

        return ticketMessageRepository
                .findAllByTicketIdAndClinicId(
                        ticketId,
                        clinicId
                )
                .stream()
                .map(this::toResponse)
                .toList();
    }


    // =========================================================
    // Support messages
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<TicketMessageResponseDto> getSupportMessages(
            Long ticketId) {

        /*
         * Support می‌تواند Ticket هر Clinic را مشاهده کند.
         */
        ticketRepository
                .findByIdAndDeletedFalse(ticketId)
                .orElseThrow(() ->
                        new NotFoundException(
                                ErrorCode.USER_NOT_FOUND
                        )
                );

        return ticketMessageRepository
                .findAllByTicketId(ticketId)
                .stream()
                .map(this::toResponse)
                .toList();
    }


    // =========================================================
    // Validation
    // =========================================================

    private void validateCanReceiveMessage(
            Ticket ticket) {

        if (ticket.getStatus()
                == TicketStatus.CLOSED) {

            throw new IllegalStateException(
                    "Closed ticket cannot receive new messages"
            );
        }
    }


    // =========================================================
    // Authentication
    // =========================================================

    private JwtAuthentication getAuthentication() {

        return (JwtAuthentication)
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
    }


    // =========================================================
    // Mapper
    // =========================================================

    private TicketMessageResponseDto toResponse(
            TicketMessage message) {

        return TicketMessageResponseDto.builder()
                .id(message.getId())
                .senderUserId(
                        message.getSenderUserId()
                )
                .type(
                        message.getType()
                )
                .message(
                        message.getMessage()
                )
                .createdAt(
                        message.getCreatedAt()
                )
                .build();
    }
}