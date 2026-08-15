package com.roochi.petflowinventory.reservation.repository;

import com.roochi.petflowinventory.reservation.entity.ReservationItem;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 8/5/2026
 */
public interface ReservationItemRepository extends JpaRepository<ReservationItem, Long> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select ri
            from ReservationItem ri
            where ri.id = :id
            """)
    Optional<ReservationItem> findByIdForUpdate(
            @Param("id") Long id
    );


    @Query("""
            select ri
            from ReservationItem ri
            where ri.reservation.id = :reservationId
            order by ri.id
            """)
    List<ReservationItem> findAllByReservationId(
            @Param("reservationId") Long reservationId
    );


    @Query("""
            select ri
            from ReservationItem ri
            where ri.reservation.id = :reservationId
              and ri.drug.id = :drugId
            """)
    Optional<ReservationItem> findByReservationIdAndDrugId(
            @Param("reservationId") Long reservationId,
            @Param("drugId") Long drugId
    );

}
