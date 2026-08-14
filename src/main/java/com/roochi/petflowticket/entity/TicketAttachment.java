package com.roochi.petflowticket.entity;

import com.roochi.petflowshared.entity.AuditingEntity;
import com.roochi.petflowshared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
@Entity
@Table(
        name = "ticket_attachments",
        indexes = {
                @Index(
                        name = "idx_ticket_attachment_ticket",
                        columnList = "ticket_id"
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketAttachment extends AuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "ticket_id",
            nullable = false
    )
    private Ticket ticket;

    @Column(
            nullable = false,
            length = 255
    )
    private String originalFileName;

    @Column(
            nullable = false,
            length = 255
    )
    private String storedFileName;

    @Column(
            nullable = false,
            length = 500
    )
    private String storageKey;

    @Column(
            nullable = false,
            length = 150
    )
    private String contentType;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    private Long uploadedByUserId;
}