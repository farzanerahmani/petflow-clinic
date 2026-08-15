package com.roochi.petflowcommunication.notification.repository;

import com.roochi.petflowcommunication.notification.entity.Notification;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    @Query("""
            select n
            from Notification n
            where n.deleted = false
              and (
                    (
                        n.status = :pending
                        and (
                            n.nextAttemptAt is null
                            or n.nextAttemptAt <= :now
                        )
                    )
                    or
                    (
                        n.status = :failed
                        and n.attemptCount < :maxAttempts
                        and n.nextAttemptAt <= :now
                    )
                  )
              and (
                    n.scheduledAt is null
                    or n.scheduledAt <= :now
                  )
            order by n.createdAt asc
            """)
    List<Notification> findProcessableNotifications(
            @Param("pending") NotificationStatus pending,
            @Param("failed") NotificationStatus failed,
            @Param("maxAttempts") Integer maxAttempts,
            @Param("now") LocalDateTime now
    );

    @Query("""
        select n
        from Notification n
        where n.deleted = false
          and n.status = :status
          and n.lastAttemptAt <= :threshold
        """)
    List<Notification> findStuckNotifications(
            @Param("status") NotificationStatus status,
            @Param("threshold") LocalDateTime threshold
    );
}