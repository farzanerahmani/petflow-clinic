package com.roochi.petflowticket.controller;

import com.roochi.petflowticket.dto.request.AddTicketMessageRequestDto;
import com.roochi.petflowticket.dto.request.AssignTicketRequestDto;
import com.roochi.petflowticket.dto.request.ChangeTicketStatusRequestDto;
import com.roochi.petflowticket.dto.request.CloseTicketRequestDto;
import com.roochi.petflowticket.dto.request.TicketStatusFilterRequestDto;
import com.roochi.petflowticket.dto.response.TicketAttachmentResponseDto;
import com.roochi.petflowticket.dto.response.TicketMessageResponseDto;
import com.roochi.petflowticket.dto.response.TicketResponseDto;
import com.roochi.petflowticket.service.TicketAttachmentService;
import com.roochi.petflowticket.service.TicketMessageService;
import com.roochi.petflowticket.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Ticket APIs for software support team.
 *
 * @author farzane.rahmani
 */
@RestController
@RequestMapping("/api/support/tickets")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('SUPPORT', 'SUPPORT_ADMIN')")
public class TicketSupportController {

    private final TicketService ticketService;

    private final TicketMessageService ticketMessageService;

    private final TicketAttachmentService ticketAttachmentService;


    // =========================================================
    // Tickets
    // =========================================================

    @GetMapping
    public ResponseEntity<Page<TicketResponseDto>> getTickets(
            @ModelAttribute
            TicketStatusFilterRequestDto requestDto,
            Pageable pageable) {

        return ResponseEntity.ok(
                ticketService.getSupportTickets(
                        requestDto,
                        pageable
                )
        );
    }


    // =========================================================
    // Assignment
    // =========================================================

    @PostMapping("/{ticketId}/assign")
    public ResponseEntity<TicketResponseDto> assign(
            @PathVariable Long ticketId,
            @Valid
            @RequestBody
            AssignTicketRequestDto requestDto) {

        requestDto.setTicketId(ticketId);

        return ResponseEntity.ok(
                ticketService.assign(
                        requestDto
                )
        );
    }


    // =========================================================
    // Status
    // =========================================================

    @PatchMapping("/{ticketId}/status")
    public ResponseEntity<TicketResponseDto> changeStatus(
            @PathVariable Long ticketId,
            @Valid
            @RequestBody
            ChangeTicketStatusRequestDto requestDto) {

        requestDto.setTicketId(ticketId);

        return ResponseEntity.ok(
                ticketService.changeStatus(
                        requestDto
                )
        );
    }


    // =========================================================
    // Close
    // =========================================================

    @PostMapping("/{ticketId}/close")
    @PreAuthorize("hasRole('SUPPORT_ADMIN')")
    public ResponseEntity<TicketResponseDto> close(
            @PathVariable Long ticketId,
            @Valid
            @RequestBody
            CloseTicketRequestDto requestDto) {

        requestDto.setTicketId(ticketId);

        return ResponseEntity.ok(
                ticketService.close(
                        requestDto
                )
        );
    }


    // =========================================================
    // Messages
    // =========================================================

    @PostMapping("/{ticketId}/messages")
    public ResponseEntity<TicketMessageResponseDto> addMessage(
            @PathVariable Long ticketId,
            @Valid
            @RequestBody
            AddTicketMessageRequestDto requestDto) {

        requestDto.setTicketId(ticketId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ticketMessageService
                                .addSupportMessage(
                                        requestDto
                                )
                );
    }


    @GetMapping("/{ticketId}/messages")
    public ResponseEntity<List<TicketMessageResponseDto>>
    getMessages(
            @PathVariable Long ticketId) {

        return ResponseEntity.ok(
                ticketMessageService
                        .getSupportMessages(
                                ticketId
                        )
        );
    }


    // =========================================================
// Attachments
// =========================================================

    @PostMapping(
            value = "/{ticketId}/attachments",
            consumes = "multipart/form-data"
    )
    public ResponseEntity<TicketAttachmentResponseDto>
    uploadAttachment(
            @PathVariable Long ticketId,
            @RequestParam("file")
            MultipartFile file) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ticketAttachmentService
                                .uploadForSupport(
                                        ticketId,
                                        file
                                )
                );
    }


    @GetMapping("/{ticketId}/attachments")
    public ResponseEntity<List<TicketAttachmentResponseDto>>
    getAttachments(
            @PathVariable Long ticketId) {

        return ResponseEntity.ok(
                ticketAttachmentService
                        .getSupportAttachments(
                                ticketId
                        )
        );
    }


    @GetMapping(
            "/{ticketId}/attachments/{attachmentId}/download"
    )
    public ResponseEntity<Resource>
    downloadAttachment(
            @PathVariable Long ticketId,
            @PathVariable Long attachmentId) {

        Resource resource =
                ticketAttachmentService
                        .downloadForSupport(
                                ticketId,
                                attachmentId
                        );

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                resource.getFilename() +
                                "\""
                )
                .body(resource);
    }


    @DeleteMapping(
            "/{ticketId}/attachments/{attachmentId}"
    )
    @PreAuthorize("hasRole('SUPPORT_ADMIN')")
    public ResponseEntity<Void>
    deleteAttachment(
            @PathVariable Long ticketId,
            @PathVariable Long attachmentId) {

        ticketAttachmentService
                .deleteForSupport(
                        ticketId,
                        attachmentId
                );

        return ResponseEntity.noContent()
                .build();
    }
}
