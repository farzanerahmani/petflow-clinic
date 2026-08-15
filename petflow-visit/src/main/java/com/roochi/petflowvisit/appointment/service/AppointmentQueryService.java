package com.roochi.petflowvisit.appointment.service;

import com.roochi.petflowvisit.appointment.dto.request.AppointmentSearchRequestDto;
import com.roochi.petflowvisit.appointment.dto.response.AppointmentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
public interface AppointmentQueryService {

    AppointmentResponseDto findById(
            Long id
    );

    Page<AppointmentResponseDto> search(
            AppointmentSearchRequestDto request,
            Pageable pageable
    );
}