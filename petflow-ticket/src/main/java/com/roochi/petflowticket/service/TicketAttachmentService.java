package com.roochi.petflowticket.service;

import com.roochi.petflowticket.dto.response.TicketAttachmentResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
public interface TicketAttachmentService {

    TicketAttachmentResponseDto uploadForClinic(
            Long ticketId,
            MultipartFile file
    );

    TicketAttachmentResponseDto uploadForSupport(
            Long ticketId,
            MultipartFile file
    );

    List<TicketAttachmentResponseDto> getClinicAttachments(
            Long ticketId
    );

    List<TicketAttachmentResponseDto> getSupportAttachments(
            Long ticketId
    );

    Resource downloadForClinic(
            Long ticketId,
            Long attachmentId
    );

    Resource downloadForSupport(
            Long ticketId,
            Long attachmentId
    );

    void deleteForClinic(
            Long ticketId,
            Long attachmentId
    );

    void deleteForSupport(
            Long ticketId,
            Long attachmentId
    );
}
