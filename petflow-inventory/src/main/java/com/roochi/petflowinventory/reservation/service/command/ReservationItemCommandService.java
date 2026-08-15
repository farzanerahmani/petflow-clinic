package com.roochi.petflowinventory.reservation.service.command;

import com.roochi.petflowinventory.reservation.dto.request.*;
import com.roochi.petflowinventory.reservation.dto.response.*;

/**
 * @author farzane.rahmani
 * @created 8/6/2026
 */
public interface ReservationItemCommandService {


    AddReservationItemResponseDto addReservationItem(AddReservationItemRequestDto requestDto);


    UpdateReservationItemResponseDto updateReservationItem(UpdateReservationItemRequestDto requestDto);


    DeleteReservationItemResponseDto deleteReservationItem(DeleteReservationItemRequestDto requestDto);

}
