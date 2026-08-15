package com.roochi.petflowdashboard.repository;

import com.roochi.petflowinventory.reservation.entity.Reservation;
import com.roochi.petflowinventory.reservation.entity.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface ReservationDashboardRepository
        extends JpaRepository<Reservation, Long> {

    @Query("""
            select count(r)
            from Reservation r
            where r.status = :status
              and r.warehouse.clinicId = :clinicId
            """)
    long countByStatus(
            @Param("clinicId") Long clinicId,
            @Param("status") ReservationStatus status
    );
}
