package com.roochi.petflowcommunication.template.repository;

import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import com.roochi.petflowcommunication.template.entity.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
public interface NotificationTemplateRepository
        extends JpaRepository<NotificationTemplate, Long> {

    @Query("""
            select t
            from NotificationTemplate t
            where t.code = :code
              and t.channel = :channel
              and t.deleted = false
            """)
    Optional<NotificationTemplate> findByCodeAndChannel(
            @Param("code") String code,
            @Param("channel") NotificationChannel channel
    );


    @Query("""
            select t
            from NotificationTemplate t
            where t.code = :code
              and t.channel = :channel
              and t.deleted = false
              and t.active = true
            """)
    Optional<NotificationTemplate> findActiveByCodeAndChannel(
            @Param("code") String code,
            @Param("channel") NotificationChannel channel
    );
}