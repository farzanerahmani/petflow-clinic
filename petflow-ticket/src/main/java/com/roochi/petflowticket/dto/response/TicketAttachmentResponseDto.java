package com.roochi.petflowticket.dto.response;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketAttachmentResponseDto {

    private Long id;

    private String originalFileName;

    private String contentType;

    private Long fileSize;

    private Long uploadedByUserId;

    private LocalDateTime createdAt;
}
