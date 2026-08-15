package com.roochi.petflowvisit.appointment.controller;

import com.roochi.petflowvisit.appointment.dto.request.AppointmentSearchRequestDto;
import com.roochi.petflowvisit.appointment.dto.request.CancelAppointmentRequestDto;
import com.roochi.petflowvisit.appointment.dto.request.CheckInAppointmentRequestDto;
import com.roochi.petflowvisit.appointment.dto.request.ConfirmAppointmentRequestDto;
import com.roochi.petflowvisit.appointment.dto.request.CreateAppointmentRequestDto;
import com.roochi.petflowvisit.appointment.dto.request.UpdateAppointmentRequestDto;
import com.roochi.petflowvisit.appointment.dto.response.AppointmentResponseDto;
import com.roochi.petflowvisit.appointment.dto.response.CreateAppointmentResponseDto;
import com.roochi.petflowvisit.appointment.dto.response.UpdateAppointmentResponseDto;
import com.roochi.petflowvisit.appointment.service.AppointmentCommandService;
import com.roochi.petflowvisit.appointment.service.AppointmentQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentCommandService appointmentCommandService;

    private final AppointmentQueryService appointmentQueryService;


    @PostMapping
    public ResponseEntity<CreateAppointmentResponseDto> create(
            @Valid
            @RequestBody
            CreateAppointmentRequestDto request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        appointmentCommandService.create(
                                request
                        )
                );
    }


    @PutMapping
    public ResponseEntity<UpdateAppointmentResponseDto> update(
            @Valid
            @RequestBody
            UpdateAppointmentRequestDto request
    ) {

        return ResponseEntity.ok(
                appointmentCommandService.update(
                        request
                )
        );
    }


    @PostMapping("/confirm")
    public ResponseEntity<Void> confirm(
            @Valid
            @RequestBody
            ConfirmAppointmentRequestDto request
    ) {

        appointmentCommandService.confirm(
                request
        );

        return ResponseEntity.ok().build();
    }


    @PostMapping("/check-in")
    public ResponseEntity<Void> checkIn(
            @Valid
            @RequestBody
            CheckInAppointmentRequestDto request
    ) {

        appointmentCommandService.checkIn(
                request
        );

        return ResponseEntity.ok().build();
    }


    @PostMapping("/cancel")
    public ResponseEntity<Void> cancel(
            @Valid
            @RequestBody
            CancelAppointmentRequestDto request
    ) {

        appointmentCommandService.cancel(
                request
        );

        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                appointmentQueryService.findById(
                        id
                )
        );
    }


    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDto>> search(
            @ModelAttribute
            AppointmentSearchRequestDto request,

            @PageableDefault(
                    size = 20,
                    sort = "appointmentDate"
            )
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                appointmentQueryService.search(
                        request,
                        pageable
                )
        );
    }
}
