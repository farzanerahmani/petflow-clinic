package com.roochi.petflowinventory.reservation.service.impl;

import com.roochi.petflowinventory.reservation.dto.request.*;
import com.roochi.petflowinventory.reservation.dto.response.*;
import com.roochi.petflowinventory.reservation.entity.Reservation;
import com.roochi.petflowinventory.reservation.entity.ReservationItem;
import com.roochi.petflowinventory.reservation.entity.enums.ReservationStatus;
import com.roochi.petflowinventory.reservation.repository.ReservationItemRepository;
import com.roochi.petflowinventory.reservation.repository.ReservationRepository;
import com.roochi.petflowinventory.reservation.service.command.ReservationItemCommandService;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.drug.repository.DrugRepository;
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
public class ReservationItemCommandServiceImpl implements ReservationItemCommandService {


    private final ReservationRepository reservationRepository;

    private final ReservationItemRepository reservationItemRepository;

    private final DrugRepository drugRepository;



    @Override
    public AddReservationItemResponseDto addReservationItem(AddReservationItemRequestDto requestDto) {


        Reservation reservation =
                reservationRepository.findByIdForUpdate(
                                requestDto.getReservationId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                ));


        if(reservation.getStatus()
                != ReservationStatus.ACTIVE){

            throw new NotFoundException(
                    ErrorCode.INTERNAL_ERROR
            );
        }


        Drug drug =
                drugRepository.findById(
                                requestDto.getDrugId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                ));


        ReservationItem item =
                ReservationItem.builder()
                        .reservation(reservation)
                        .drug(drug)
                        .quantity(requestDto.getQuantity())
                        .expirationDate(requestDto.getExpirationDate())
                        .batchNumber(requestDto.getBatchNumber())
                        .build();


        reservationItemRepository.save(item);


        return AddReservationItemResponseDto.builder()
                .id(item.getId())
                .build();

    }



    @Override
    public UpdateReservationItemResponseDto updateReservationItem(
            UpdateReservationItemRequestDto requestDto) {


        ReservationItem item =
                reservationItemRepository.findByIdForUpdate(
                                requestDto.getId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                ));


        if(item.getReservation().getStatus()
                != ReservationStatus.ACTIVE){

            throw new NotFoundException(
                    ErrorCode.INTERNAL_ERROR
            );
        }


        Drug drug =
                drugRepository.findById(
                                requestDto.getDrugId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                ));


        item.setDrug(drug);

        item.setQuantity(requestDto.getQuantity());
        item.setBatchNumber(requestDto.getBatchNumber());
        item.setExpirationDate(requestDto.getExpirationDate());

        return UpdateReservationItemResponseDto.builder()
                .id(item.getId())
                .build();

    }




    @Override
    public DeleteReservationItemResponseDto deleteReservationItem(
            DeleteReservationItemRequestDto requestDto) {


        ReservationItem item =
                reservationItemRepository.findByIdForUpdate(
                                requestDto.getId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                ));



        if(item.getReservation().getStatus()
                != ReservationStatus.ACTIVE){

            throw new NotFoundException(
                    ErrorCode.INTERNAL_ERROR
            );
        }


        Long id = item.getId();


        reservationItemRepository.delete(item);



        return DeleteReservationItemResponseDto.builder()
                .id(id)
                .message(
                        "Reservation item deleted successfully"
                )
                .build();

    }

}
