package com.roochi.petflowcommunication.reminder.repository;

import com.roochi.petflowcommunication.reminder.entity.Reminder;
import com.roochi.petflowcommunication.reminder.entity.enums.ReminderStatus;
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
public interface ReminderRepository
        extends JpaRepository<Reminder, Long> {

    @Query("""
            select r
            from Reminder r
            left join fetch r.template
            where r.id = :id
              and r.deleted = false
            """)
    Optional<Reminder> findActiveById(
            @Param("id") Long id
    );


    @Query("""
            select distinct r
            from Reminder r
            join fetch r.template t
            where r.deleted = false
              and r.status = :status
              and r.scheduledAt <= :now
              and t.deleted = false
              and t.active = true
            order by r.scheduledAt asc
            """)
    List<Reminder> findDueReminders(
            @Param("status") ReminderStatus status,
            @Param("now") LocalDateTime now
    );
}