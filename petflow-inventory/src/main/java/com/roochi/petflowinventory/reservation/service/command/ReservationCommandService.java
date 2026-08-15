package com.roochi.petflowinventory.reservation.service.command;

import com.roochi.petflowinventory.reservation.dto.request.*;
import com.roochi.petflowinventory.reservation.dto.response.*;

/**
 * @author farzane.rahmani
 * @created 8/6/2026
 */
public interface ReservationCommandService {


    AddReservationResponseDto addReservation(AddReservationRequestDto requestDto);


    UpdateReservationResponseDto updateReservation(UpdateReservationRequestDto requestDto);


    ReleaseReservationResponseDto releaseReservation(ReleaseReservationRequestDto requestDto);

    ReserveReservationResponseDto reserveReservation(ReserveReservationRequestDto requestDto);

    CompleteReservationResponseDto completeReservation(CompleteReservationRequestDto requestDto);

}
