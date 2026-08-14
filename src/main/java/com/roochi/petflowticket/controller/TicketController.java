package com.roochi.petflowticket.controller;

import com.roochi.petflowticket.dto.request.AddTicketMessageRequestDto;
import com.roochi.petflowticket.dto.request.CloseTicketRequestDto;
import com.roochi.petflowticket.dto.request.CreateTicketRequestDto;
import com.roochi.petflowticket.dto.response.TicketAttachmentResponseDto;
import com.roochi.petflowticket.dto.response.TicketDetailsResponseDto;
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
 * Ticket APIs for clinic users.
 *
 * @author farzane.rahmani
 */
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('CLINIC_USER', 'CLINIC_ADMIN')")
public class TicketController {

    private final TicketService ticketService;

    private final TicketMessageService ticketMessageService;

    private final TicketAttachmentService ticketAttachmentService;


    // =========================================================
    // Ticket
    // =========================================================

    @PostMapping
    public ResponseEntity<TicketResponseDto> create(
            @Valid
            @RequestBody
            CreateTicketRequestDto requestDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ticketService.create(
                                requestDto
                        )
                );
    }


    @GetMapping
    public ResponseEntity<Page<TicketResponseDto>> getMyTickets(
            Pageable pageable) {

        return ResponseEntity.ok(
                ticketService.getMyTickets(
                        pageable
                )
        );
    }


    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDetailsResponseDto> getById(
            @PathVariable Long ticketId) {

        return ResponseEntity.ok(
                ticketService.getById(
                        ticketId
                )
        );
    }


    @PostMapping("/{ticketId}/reopen")
    public ResponseEntity<TicketResponseDto> reopen(
            @PathVariable Long ticketId) {

        return ResponseEntity.ok(
                ticketService.reopen(
                        ticketId
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
                                .addClinicUserMessage(
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
                        .getClinicMessages(
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
                                .uploadForClinic(
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
                        .getClinicAttachments(
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
                        .downloadForClinic(
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
    public ResponseEntity<Void>
    deleteAttachment(
            @PathVariable Long ticketId,
            @PathVariable Long attachmentId) {

        ticketAttachmentService
                .deleteForClinic(
                        ticketId,
                        attachmentId
                );

        return ResponseEntity.noContent()
                .build();
    }
}