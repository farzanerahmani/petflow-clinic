package com.roochi.petflowdashboard.repository;

import com.roochi.petflowvisit.appointment.entity.Appointment;
import com.roochi.petflowvisit.appointment.entity.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface AppointmentDashboardRepository
        extends JpaRepository<Appointment, Long> {

    @Query("""
            select a.status, count(a)
            from Appointment a
            where a.clinicId = :clinicId
              and a.appointmentDate >= :from
              and a.appointmentDate < :to
            group by a.status
            """)
    List<Object[]> countByStatus(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );
}