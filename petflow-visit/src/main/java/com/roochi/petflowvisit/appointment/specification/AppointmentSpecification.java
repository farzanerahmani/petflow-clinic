package com.roochi.petflowvisit.appointment.specification;

import com.roochi.petflowvisit.appointment.entity.Appointment;
import com.roochi.petflowvisit.appointment.entity.enums.AppointmentStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


public final class AppointmentSpecification {

    private AppointmentSpecification() {
    }

    public static Specification<Appointment> clinicId(
            Long clinicId
    ) {
        return (root, query, cb) -> {

            if (clinicId == null) {
                return null;
            }

            return cb.equal(
                    root.get("clinicId"),
                    clinicId
            );
        };
    }


    public static Specification<Appointment> petId(
            Long petId
    ) {
        return (root, query, cb) -> {

            if (petId == null) {
                return null;
            }

            return cb.equal(
                    root.get("petId"),
                    petId
            );
        };
    }


    public static Specification<Appointment> doctorUserId(
            Long doctorUserId
    ) {
        return (root, query, cb) -> {

            if (doctorUserId == null) {
                return null;
            }

            return cb.equal(
                    root.get("doctorUserId"),
                    doctorUserId
            );
        };
    }


    public static Specification<Appointment> status(
            AppointmentStatus status
    ) {
        return (root, query, cb) -> {

            if (status == null) {
                return null;
            }

            return cb.equal(
                    root.get("status"),
                    status
            );
        };
    }



    public static Specification<Appointment> statuses(
            Collection<AppointmentStatus> statuses
    ) {
        return (root, query, cb) -> {

            if (statuses == null || statuses.isEmpty()) {
                return null;
            }

            return root.get("status")
                    .in(statuses);
        };
    }


    public static Specification<Appointment> appointmentDateFrom(
            LocalDateTime from
    ) {
        return (root, query, cb) -> {

            if (from == null) {
                return null;
            }

            return cb.greaterThanOrEqualTo(
                    root.get("appointmentDate"),
                    from
            );
        };
    }


    public static Specification<Appointment> appointmentDateTo(
            LocalDateTime to
    ) {
        return (root, query, cb) -> {

            if (to == null) {
                return null;
            }

            return cb.lessThanOrEqualTo(
                    root.get("appointmentDate"),
                    to
            );
        };
    }


    public static Specification<Appointment> notDeleted() {
        return (root, query, cb) ->
                cb.isFalse(
                        root.get("deleted")
                );
    }
}
