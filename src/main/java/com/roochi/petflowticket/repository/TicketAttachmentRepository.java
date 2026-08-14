package com.roochi.petflowticket.repository;

import com.roochi.petflowticket.entity.TicketAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
public interface TicketAttachmentRepository
        extends JpaRepository<TicketAttachment, Long> {

    @Query("""
            select ta
            from TicketAttachment ta
            where ta.ticket.id = :ticketId
            order by ta.createdAt asc
            """)
    List<TicketAttachment> findAllByTicketId(
            @Param("ticketId") Long ticketId
    );


    @Query("""
            select ta
            from TicketAttachment ta
            join fetch ta.ticket t
            where ta.id = :attachmentId
              and t.id = :ticketId
              and t.clinicId = :clinicId
              and t.deleted = false
            """)
    Optional<TicketAttachment>
    findByIdAndTicketIdAndClinicId(
            @Param("attachmentId") Long attachmentId,
            @Param("ticketId") Long ticketId,
            @Param("clinicId") Long clinicId
    );


    @Query("""
            select ta
            from TicketAttachment ta
            join fetch ta.ticket t
            where ta.id = :attachmentId
              and t.id = :ticketId
              and t.deleted = false
            """)
    Optional<TicketAttachment>
    findByIdAndTicketId(
            @Param("attachmentId") Long attachmentId,
            @Param("ticketId") Long ticketId
    );


    @Query("""
            select ta.storageKey
            from TicketAttachment ta
            where ta.storageKey is not null
            """)
    List<String> findAllStorageKeys();


    long countByTicketId(
            Long ticketId
    );
}