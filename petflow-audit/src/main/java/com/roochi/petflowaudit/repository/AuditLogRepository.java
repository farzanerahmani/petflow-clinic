package com.roochi.petflowaudit.repository;

import com.roochi.petflowaudit.entity.AuditLog;
import com.roochi.petflowaudit.entity.enums.AuditAction;
import com.roochi.petflowaudit.entity.enums.AuditEntityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    @Query("""
            select a
            from AuditLog a
            where (:clinicId is null or a.clinicId = :clinicId)
              and (:userId is null or a.userId = :userId)
              and (:action is null or a.action = :action)
              and (:entityType is null or a.entityType = :entityType)
              and (:entityId is null or a.entityId = :entityId)
              and (:from is null or a.eventAt >= :from)
              and (:to is null or a.eventAt <= :to)
            order by a.eventAt desc
            """)
    Page<AuditLog> search(
            @Param("clinicId") Long clinicId,
            @Param("userId") Long userId,
            @Param("action") AuditAction action,
            @Param("entityType") AuditEntityType entityType,
            @Param("entityId") Long entityId,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to,
            Pageable pageable
    );

    @Query("""
        select a
        from AuditLog a
        where a.id = :id
          and a.clinicId = :clinicId
        """)
    Optional<AuditLog> findByIdAndClinicId(
            @Param("id") Long id,
            @Param("clinicId") Long clinicId
    );
}
