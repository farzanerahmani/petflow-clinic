package com.roochi.petflowticket.entity;

import com.roochi.petflowshared.entity.ClinicSoftDeleteEntity;
import com.roochi.petflowticket.entity.enums.TicketCategory;
import com.roochi.petflowticket.entity.enums.TicketModule;
import com.roochi.petflowticket.entity.enums.TicketPriority;
import com.roochi.petflowticket.entity.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */


/**
 * Support ticket raised by a clinic user.
 *
 * @author farzane.rahmani
 */
@Entity
@Table(
        name = "tickets",
        indexes = {

                @Index(
                        name = "idx_ticket_clinic_status",
                        columnList = "clinic_id, status"
                ),

                @Index(
                        name = "idx_ticket_clinic_created",
                        columnList = "clinic_id, created_at"
                ),

                @Index(
                        name = "idx_ticket_status_priority",
                        columnList = "status, priority"
                ),

                @Index(
                        name = "idx_ticket_assigned_status",
                        columnList = "assigned_to_user_id, status"
                ),

                @Index(
                        name = "idx_ticket_module_reference",
                        columnList = "module, reference_id"
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends ClinicSoftDeleteEntity {

    @Column(
            nullable = false,
            unique = true,
            length = 30
    )
    private String ticketNumber;

    @Column(
            nullable = false,
            length = 200
    )
    private String title;

    @Column(
            nullable = false,
            length = 5000
    )
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 30
    )
    private TicketCategory category;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private TicketPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 30
    )
    private TicketStatus status;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 40
    )
    private TicketModule module;

    /**
     * User who created the ticket.
     */
    @Column(nullable = false)
    private Long createdByUserId;

    /**
     * Support user assigned to the ticket.
     */
    private Long assignedToUserId;

    /**
     * Optional reference to the business object
     * related to the problem.
     *
     * Example:
     * module = SALE
     * referenceId = sale.id
     */
    private Long referenceId;

    private LocalDateTime resolvedAt;

    private LocalDateTime closedAt;

    /**
     * Application version in which
     * the issue was fixed.
     */
    @Column(length = 30)
    private String resolvedInVersion;

    @Column(length = 2000)
    private String resolution;
}
