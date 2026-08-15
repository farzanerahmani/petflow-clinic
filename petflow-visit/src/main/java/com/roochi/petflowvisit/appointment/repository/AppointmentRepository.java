package com.roochi.petflowvisit.appointment.repository;

import com.roochi.petflowvisit.appointment.entity.Appointment;
import com.roochi.petflowvisit.appointment.entity.enums.AppointmentStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long>,
        JpaSpecificationExecutor<Appointment> {

    @Query("""
            select a
            from Appointment a
            where a.id = :id
              and a.deleted = false
            """)
    Optional<Appointment> findActiveById(
            @Param("id") Long id
    );


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select a
            from Appointment a
            where a.id = :id
              and a.deleted = false
            """)
    Optional<Appointment> findByIdForUpdate(
            @Param("id") Long id
    );


    @Query("""
            select case when count(a) > 0 then true else false end
            from Appointment a
            where a.deleted = false
              and a.doctorUserId = :doctorUserId
              and a.appointmentDate = :appointmentDate
              and a.status in :statuses
              and (:appointmentId is null or a.id <> :appointmentId)
            """)
    boolean existsDoctorConflict(
            @Param("doctorUserId") Long doctorUserId,
            @Param("appointmentDate") LocalDateTime appointmentDate,
            @Param("statuses") Collection<AppointmentStatus> statuses,
            @Param("appointmentId") Long appointmentId
    );
}