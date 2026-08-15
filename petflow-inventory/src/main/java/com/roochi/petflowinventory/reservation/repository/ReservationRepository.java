package com.roochi.petflowinventory.reservation.repository;

import com.roochi.petflowinventory.reservation.entity.Reservation;
import com.roochi.petflowinventory.reservation.entity.enums.ReservationStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 8/5/2026
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {


    Optional<Reservation> findByReservationNumber(String reservationNumber);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select r
            from Reservation r
            where r.id = :id
            """)
    Optional<Reservation> findByIdForUpdate(
            @Param("id") Long id);


    @Query("""
            select r
            from Reservation r
            where r.warehouse.id = :warehouseId
              and r.status = :status
            order by r.createdAt desc
            """)
    List<Reservation> findAllByWarehouseAndStatus(
            @Param("warehouseId") Long warehouseId,
            @Param("status") ReservationStatus status
    );


    @Query("""
            select r
            from Reservation r
            where r.referenceType = :referenceType
              and r.referenceId = :referenceId
            order by r.createdAt desc
            """)
    List<Reservation> findAllByReference(
            @Param("referenceType") String referenceType,
            @Param("referenceId") Long referenceId
    );

}
