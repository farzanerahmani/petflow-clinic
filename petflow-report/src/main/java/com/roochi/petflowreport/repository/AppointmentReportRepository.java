package com.roochi.petflowreport.repository;

import com.roochi.petflowvisit.appointment.entity.Appointment;
import com.roochi.petflowvisit.appointment.entity.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */


public interface AppointmentReportRepository
        extends JpaRepository<Appointment, Long> {

    @Query("""
        select a
        from Appointment a
        where a.clinicId = :clinicId
          and a.deleted = false
          and (:from is null or a.appointmentDate >= :from)
          and (:to is null or a.appointmentDate <= :to)
          and (:doctorUserId is null or a.doctorUserId = :doctorUserId)
          and (:petId is null or a.petId = :petId)
          and (:status is null or a.status = :status)
        order by a.appointmentDate desc
        """)
    List<Appointment> getAppointmentReport(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to,
            @Param("doctorUserId") Long doctorUserId,
            @Param("petId") Long petId,
            @Param("status") AppointmentStatus status
    );
}
