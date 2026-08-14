package com.roochi.petflowticket.entity;

import com.roochi.petflowshared.entity.BaseEntity;
import com.roochi.petflowticket.entity.enums.TicketMessageType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */


@Entity
@Table(
        name = "ticket_messages",
        indexes = {

                @Index(
                        name = "idx_ticket_message_ticket_created",
                        columnList = "ticket_id, created_at"
                ),

                @Index(
                        name = "idx_ticket_message_sender",
                        columnList = "sender_user_id"
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketMessage extends BaseEntity {

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "ticket_id",
            nullable = false
    )
    private Ticket ticket;

    @Column(nullable = false)
    private Long senderUserId;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 30
    )
    private TicketMessageType type;

    @Column(
            nullable = false,
            length = 5000
    )
    private String message;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
