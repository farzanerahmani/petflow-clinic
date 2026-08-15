package com.roochi.petflowvisit.visit.service.impl;

import com.roochi.petflowpet.dto.request.GetPetByIdRequestDto;
import com.roochi.petflowpet.facade.PetFacade;
import com.roochi.petflowshared.exception.AlreadyExistsException;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowshared.security.JwtAuthentication;
import com.roochi.petflowvisit.appointment.entity.Appointment;
import com.roochi.petflowvisit.appointment.entity.enums.AppointmentStatus;
import com.roochi.petflowvisit.appointment.repository.AppointmentRepository;
import com.roochi.petflowvisit.appointment.service.AppointmentCommandService;
import com.roochi.petflowvisit.appointment.service.AppointmentQueryService;
import com.roochi.petflowvisit.dto.request.visit.*;
import com.roochi.petflowvisit.dto.response.visit.*;
import com.roochi.petflowvisit.visit.mapper.VisitMapper;
import com.roochi.petflowvisit.visit.entity.Visit;
import com.roochi.petflowvisit.visit.entity.enums.VisitStatus;
import com.roochi.petflowvisit.visit.repository.VisitRepository;
import com.roochi.petflowvisit.visit.service.command.VisitCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/10/2026
 */
@Service
@Transactional
@RequiredArgsConstructor
public class VisitCommandServiceImpl implements VisitCommandService {

    private final PetFacade petFacade;

    private final VisitRepository visitRepository;

    private final VisitMapper visitMapper;

    private final AppointmentCommandService appointmentCommandService;

    private final AppointmentRepository appointmentRepository;

    @Override
    public AddVisitResponseDto addVisit(AddVisitRequestDto requestDto) {

        JwtAuthentication authentication = (JwtAuthentication) SecurityContextHolder
                .getContext().getAuthentication();

        Long clinicId = authentication.getClinicId();
        petFacade.getPet(GetPetByIdRequestDto.builder()
                .id(requestDto.getPetId()).build());
        Appointment appointment = null;

        if (requestDto.getAppointmentId() != null) {

            appointment =
                    validateAppointmentForVisit(
                            requestDto.getAppointmentId(),
                            requestDto.getPetId(),
                            requestDto.getDoctorUserId()
                    );
        }

        Visit visit = Visit.builder()
                .clinicId(clinicId)
                .petId(requestDto.getPetId())
                .doctorUserId(requestDto.getDoctorUserId())
                .visitDate(requestDto.getVisitDate())
                .chiefComplaint(requestDto.getChiefComplaint())
                .description(requestDto.getDiagnosis())
                .description(requestDto.getDescription())
                .weight(requestDto.getWeight())
                .temperature(requestDto.getTemperature())
                .heartRate(requestDto.getHeartRate())
                .respiratoryRate(requestDto.getRespiratoryRate())
                .type(requestDto.getType())
                .status(VisitStatus.SCHEDULED)
                .build();

        visitRepository.save(visit);
        AddVisitResponseDto responseDto = new AddVisitResponseDto();
        responseDto.setVisit(visitMapper.toVisitDto(visit));
        return responseDto;
    }

    @Override
    public UpdateVisitResponseDto updateVisit(UpdateVisitRequestDto requestDto) {
        JwtAuthentication authentication = (JwtAuthentication) SecurityContextHolder.getContext()
                .getAuthentication();

        Long clinicId = authentication.getClinicId();

        Visit visit = visitRepository.findByIdAndClinicId(requestDto.getId(), clinicId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        visit.setDoctorUserId(requestDto.getDoctorUserId());
        visit.setVisitDate(requestDto.getVisitDate());
        visit.setChiefComplaint(requestDto.getChiefComplaint());
        visit.setDiagnosis(requestDto.getDiagnosis());
        visit.setDescription(requestDto.getDescription());
        visit.setWeight(requestDto.getWeight());
        visit.setTemperature(requestDto.getTemperature());
        visit.setHeartRate(requestDto.getHeartRate());
        visit.setRespiratoryRate(requestDto.getRespiratoryRate());
        visit.setType(requestDto.getType());
        visitRepository.save(visit);
        UpdateVisitResponseDto responseDto = new UpdateVisitResponseDto();
        responseDto.setVisit(visitMapper.toVisitDto(visit));
        return responseDto;
    }

    @Override
    public StartVisitResponseDto startVisit(StartVisitRequestDto requestDto) {
        JwtAuthentication authentication = (JwtAuthentication)
                SecurityContextHolder.getContext()
                        .getAuthentication();

        Long clinicId = authentication.getClinicId();
        Visit visit = visitRepository.findByIdAndClinicId(requestDto.getVisitId(), clinicId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        if (visit.getStatus() != VisitStatus.SCHEDULED)
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        visit.setStatus(VisitStatus.IN_PROGRESS);
        visitRepository.save(visit);
        StartVisitResponseDto responseDto = new StartVisitResponseDto();
        responseDto.setVisit(visitMapper.toVisitDto(visit));
        return responseDto;
    }

    @Override
    public FinishVisitResponseDto finishVisit(
            FinishVisitRequestDto requestDto
    ) {

        JwtAuthentication authentication =
                (JwtAuthentication)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication();

        Long clinicId = authentication.getClinicId();

        Visit visit =
                visitRepository
                        .findByIdAndClinicId(
                                requestDto.getVisitId(),
                                clinicId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND
                                )
                        );

        if (visit.getStatus() != VisitStatus.IN_PROGRESS) {
            throw new NotFoundException(
                    ErrorCode.USER_NOT_FOUND
            );
        }

        /*
         * اگر Visit از Appointment آمده،
         * اول Appointment را کامل می‌کنیم.
         */
        if (visit.getAppointmentId() != null) {

            appointmentCommandService.complete(
                    visit.getAppointmentId()
            );
        }

        /*
         * بعد Visit را کامل می‌کنیم.
         */
        visit.setStatus(
                VisitStatus.COMPLETED
        );

        visit.setFinishedAt(
                LocalDateTime.now()
        );

        visitRepository.save(visit);

        FinishVisitResponseDto responseDto =
                new FinishVisitResponseDto();

        responseDto.setVisit(
                visitMapper.toVisitDto(visit)
        );

        return responseDto;
    }

    @Override
    public CancelVisitResponseDto cancelVisit(CancelVisitRequestDto requestDto) {
        JwtAuthentication authentication = (JwtAuthentication)
                SecurityContextHolder.getContext()
                        .getAuthentication();

        Long clinicId = authentication.getClinicId();
        Visit visit = visitRepository.findByIdAndClinicId(requestDto.getVisitId(), clinicId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        if (visit.getStatus().equals(VisitStatus.CANCELLED))
            throw new AlreadyExistsException(ErrorCode.USER_NOT_FOUND);

        visit.setStatus(VisitStatus.CANCELLED);
        visitRepository.save(visit);
        return new CancelVisitResponseDto(visit.getId());
    }

    private Appointment validateAppointmentForVisit(
            Long appointmentId,
            Long petId,
            Long doctorUserId
    ) {

        Appointment appointment =
                appointmentRepository.findByIdForUpdate(
                                appointmentId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        if (appointment.getStatus()
                != AppointmentStatus.CHECKED_IN) {

            throw new IllegalStateException(
                    "Appointment must be checked in before creating a visit."
            );
        }

        if (!appointment.getPetId().equals(petId)) {

            throw new IllegalStateException(
                    "Appointment pet does not match visit pet."
            );
        }

        if (!appointment.getDoctorUserId().equals(doctorUserId)) {

            throw new IllegalStateException(
                    "Appointment doctor does not match visit doctor."
            );
        }

        return appointment;
    }
}
