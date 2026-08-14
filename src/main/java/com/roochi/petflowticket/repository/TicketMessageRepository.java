package com.roochi.petflowticket.repository;


import com.roochi.petflowticket.entity.TicketMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */


public interface TicketMessageRepository
        extends JpaRepository<TicketMessage, Long> {

    @Query("""
            select tm
            from TicketMessage tm
            where tm.ticket.id = :ticketId
              and tm.ticket.deleted = false
            order by tm.createdAt asc
            """)
    List<TicketMessage> findAllByTicketId(
            @Param("ticketId") Long ticketId
    );

    @Query("""
            select tm
            from TicketMessage tm
            where tm.ticket.id = :ticketId
              and tm.ticket.clinicId = :clinicId
              and tm.ticket.deleted = false
            order by tm.createdAt asc
            """)
    List<TicketMessage> findAllByTicketIdAndClinicId(
            @Param("ticketId") Long ticketId,
            @Param("clinicId") Long clinicId
    );

    @Query("""
            select count(tm)
            from TicketMessage tm
            where tm.ticket.id = :ticketId
              and tm.ticket.deleted = false
            """)
    long countByTicketId(
            @Param("ticketId") Long ticketId
    );
}
