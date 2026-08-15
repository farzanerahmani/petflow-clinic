package com.roochi.petflowticket.repository;


import com.roochi.petflowticket.entity.Ticket;
import com.roochi.petflowticket.entity.enums.TicketModule;
import com.roochi.petflowticket.entity.enums.TicketStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByIdAndClinicIdAndDeletedFalse(
            Long id,
            Long clinicId
    );

    Optional<Ticket> findByTicketNumberAndClinicIdAndDeletedFalse(
            String ticketNumber,
            Long clinicId
    );

    Optional<Ticket> findByIdAndDeletedFalse(
            Long id
    );

    boolean existsByTicketNumber(String ticketNumber);

    Page<Ticket> findAllByClinicIdAndDeletedFalse(
            Long clinicId,
            Pageable pageable
    );

    Page<Ticket> findAllByClinicIdAndStatusAndDeletedFalse(
            Long clinicId,
            TicketStatus status,
            Pageable pageable
    );

    Page<Ticket> findAllByAssignedToUserIdAndStatusAndDeletedFalse(
            Long assignedToUserId,
            TicketStatus status,
            Pageable pageable
    );

    @Query("""
            select t
            from Ticket t
            where t.deleted = false
              and t.clinicId = :clinicId
              and (
                    :status is null
                    or t.status = :status
              )
              and (
                    :assignedToUserId is null
                    or t.assignedToUserId = :assignedToUserId
              )
            order by t.createdAt desc
            """)
    Page<Ticket> searchClinicTickets(
            @Param("clinicId") Long clinicId,
            @Param("status") TicketStatus status,
            @Param("assignedToUserId") Long assignedToUserId,
            Pageable pageable
    );

    /*
     * برای Support Team
     * Support باید بتواند Ticket کلینیک‌های مختلف را ببیند.
     */
    @Query("""
            select t
            from Ticket t
            where t.deleted = false
              and (
                    :status is null
                    or t.status = :status
              )
              and (
                    :assignedToUserId is null
                    or t.assignedToUserId = :assignedToUserId
              )
            order by t.createdAt desc
            """)
    Page<Ticket> searchSupportTickets(
            @Param("status") TicketStatus status,
            @Param("assignedToUserId") Long assignedToUserId,
            Pageable pageable
    );

    /*
     * Ticketهای مرتبط با یک رکورد خاص
     *
     * مثال:
     * module = SALE
     * referenceId = 100
     */
    @Query("""
            select t
            from Ticket t
            where t.deleted = false
              and t.clinicId = :clinicId
              and t.module = :module
              and t.referenceId = :referenceId
            order by t.createdAt desc
            """)
    Page<Ticket> findRelatedTickets(
            @Param("clinicId") Long clinicId,
            @Param("module") TicketModule module,
            @Param("referenceId") Long referenceId,
            Pageable pageable
    );
}
