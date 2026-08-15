package com.roochi.petflowinventory.reservation.service.impl;

import com.roochi.petflowinventory.reservation.dto.request.*;
import com.roochi.petflowinventory.reservation.dto.response.*;
import com.roochi.petflowinventory.reservation.entity.Reservation;
import com.roochi.petflowinventory.reservation.entity.enums.ReservationStatus;
import com.roochi.petflowinventory.reservation.repository.ReservationRepository;
import com.roochi.petflowinventory.reservation.service.ReservationInventorySynchronizer;
import com.roochi.petflowinventory.reservation.service.ReservationReleaseSynchronizer;
import com.roochi.petflowinventory.reservation.service.command.ReservationCommandService;
import com.roochi.petflowinventory.warehouse.repository.WarehouseRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author farzane.rahmani
 * @created 8/6/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ReservationCommandServiceImpl
        implements ReservationCommandService {

    private final ReservationRepository reservationRepository;

    private final WarehouseRepository warehouseRepository;

    private final ReservationInventorySynchronizer
            reservationInventorySynchronizer;

    private final ReservationReleaseSynchronizer
            reservationReleaseSynchronizer;


    @Override
    public AddReservationResponseDto addReservation(
            AddReservationRequestDto requestDto) {

        reservationRepository
                .findByReservationNumber(
                        requestDto.getReservationNumber()
                )
                .ifPresent(r -> {
                    throw new NotFoundException(
                            ErrorCode.INTERNAL_ERROR
                    );
                });

        var warehouse =
                warehouseRepository.findById(
                                requestDto.getWarehouseId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        Reservation reservation =
                Reservation.builder()
                        .reservationNumber(
                                requestDto.getReservationNumber()
                        )
                        .warehouse(warehouse)
                        .referenceId(
                                requestDto.getReferenceId()
                        )
                        .referenceType(
                                requestDto.getReferenceType()
                        )
                        .reservationDate(
                                requestDto.getReservationDate()
                        )
                        .status(
                                ReservationStatus.ACTIVE
                        )
                        .description(
                                requestDto.getDescription()
                        )
                        .build();

        reservationRepository.save(reservation);

        return AddReservationResponseDto.builder()
                .id(reservation.getId())
                .build();
    }


    @Override
    public UpdateReservationResponseDto updateReservation(
            UpdateReservationRequestDto requestDto) {

        Reservation reservation =
                reservationRepository.findByIdForUpdate(
                                requestDto.getId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        if (reservation.getStatus()
                != ReservationStatus.ACTIVE) {

            throw new NotFoundException(
                    ErrorCode.INTERNAL_ERROR
            );
        }

        reservation.setReservationNumber(
                requestDto.getReservationNumber()
        );

        reservation.setReservationDate(
                requestDto.getReservationDate()
        );

        reservation.setReferenceId(
                requestDto.getReferenceId()
        );

        reservation.setReferenceType(
                requestDto.getReferenceType()
        );

        reservation.setDescription(
                requestDto.getDescription()
        );

        return UpdateReservationResponseDto.builder()
                .id(reservation.getId())
                .build();
    }


    @Override
    public ReserveReservationResponseDto reserveReservation(
            ReserveReservationRequestDto requestDto) {

        Reservation reservation =
                reservationRepository.findByIdForUpdate(
                                requestDto.getReservationId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        if (reservation.getStatus()
                != ReservationStatus.ACTIVE) {

            throw new NotFoundException(
                    ErrorCode.INTERNAL_ERROR
            );
        }
        reservationInventorySynchronizer.reserve(
                reservation,
                requestDto.getDrugId(),
                requestDto.getQuantity(),
                "system"
        );

        reservation.setStatus(
                ReservationStatus.RESERVED
        );

        return ReserveReservationResponseDto.builder()
                .reservationId(
                        reservation.getId()
                )
                .message(
                        "Reservation reserved successfully"
                )
                .build();
    }


    @Override
    public ReleaseReservationResponseDto releaseReservation(
            ReleaseReservationRequestDto requestDto) {

        Reservation reservation =
                reservationRepository.findByIdForUpdate(
                                requestDto.getReservationId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        if (reservation.getStatus()
                != ReservationStatus.RESERVED) {

            throw new NotFoundException(
                    ErrorCode.INTERNAL_ERROR
            );
        }

        reservationReleaseSynchronizer.release(
                reservation,
                requestDto.getDrugId(),
                requestDto.getBatchNumber(),
                requestDto.getExpirationDate(),
                requestDto.getQuantity(),
                "system"
        );

        reservation.setStatus(
                ReservationStatus.RELEASED
        );

        return ReleaseReservationResponseDto.builder()
                .reservationId(
                        reservation.getId()
                )
                .message(
                        "Reservation released successfully"
                )
                .build();
    }


    @Override
    public CompleteReservationResponseDto completeReservation(
            CompleteReservationRequestDto requestDto) {

        Reservation reservation =
                reservationRepository.findByIdForUpdate(
                                requestDto.getReservationId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        if (reservation.getStatus()
                != ReservationStatus.RESERVED) {

            throw new NotFoundException(
                    ErrorCode.INTERNAL_ERROR
            );
        }

        /*
         * فعلاً فقط وضعیت Reservation تغییر می‌کند.
         *
         * برای مصرف واقعی Stock باید اطلاعات Drug/Quantity
         * در جریان Reservation موجود باشد.
         *
         * تا وقتی این اطلاعات در مدل فعلی وجود ندارد،
         * نباید اینجا Sale را با داده ناقص صدا بزنیم.
         */

        reservation.setStatus(
                ReservationStatus.COMPLETED
        );

        return CompleteReservationResponseDto.builder()
                .reservationId(
                        reservation.getId()
                )
                .message(
                        "Reservation completed successfully"
                )
                .build();
    }
}