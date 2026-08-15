package com.roochi.petflowvisit.hospitalization.service.impl;

import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowidentity.user.repository.UserRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.dto.request.hospitalization.AddHospitalizationDailyNoteRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.DeleteHospitalizationDailyNoteRequestDto;
import com.roochi.petflowvisit.dto.request.hospitalization.UpdateHospitalizationDailyNoteRequestDto;
import com.roochi.petflowvisit.dto.response.hospitalization.AddHospitalizationDailyNoteResponseDto;
import com.roochi.petflowvisit.dto.response.hospitalization.DeleteHospitalizationDailyNoteResponseDto;
import com.roochi.petflowvisit.dto.response.hospitalization.UpdateHospitalizationDailyNoteResponseDto;
import com.roochi.petflowvisit.hospitalization.entity.Hospitalization;
import com.roochi.petflowvisit.hospitalization.entity.HospitalizationDailyNote;
import com.roochi.petflowvisit.hospitalization.repository.HospitalizationDailyNoteRepository;
import com.roochi.petflowvisit.hospitalization.repository.HospitalizationRepository;
import com.roochi.petflowvisit.hospitalization.service.command.HospitalizationDailyNoteCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@Transactional
@RequiredArgsConstructor
@Service
public class HospitalizationDailyNoteCommandServiceImpl implements HospitalizationDailyNoteCommandService {
   private final UserRepository userRepository;
   private final HospitalizationRepository hospitalizationRepository;

   private final HospitalizationDailyNoteRepository hospitalizationDailyNoteRepository;

    @Override
    public AddHospitalizationDailyNoteResponseDto addHospitalizationDailyNote(AddHospitalizationDailyNoteRequestDto requestDto) {
        Hospitalization hospitalization =
                hospitalizationRepository.findByIdForUpdate(requestDto.getHospitalizationId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.VALIDATION_ERROR));

        User veterinarian =
                userRepository.findById(requestDto.getVeterinarianId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.USER_NOT_FOUND));

        HospitalizationDailyNote entity =
                HospitalizationDailyNote.builder()
                        .hospitalization(hospitalization)
                        .veterinarian(veterinarian)
                        .recordDateTime(requestDto.getRecordDateTime())
                        .temperature(requestDto.getTemperature())
                        .pulse(requestDto.getPulse())
                        .respiration(requestDto.getRespiration())
                        .weight(requestDto.getWeight())
                        .appetite(requestDto.getAppetite())
                        .urination(requestDto.getUrination())
                        .defecation(requestDto.getDefecation())
                        .medication(requestDto.getMedication())
                        .note(requestDto.getNote())
                        .build();

        hospitalizationDailyNoteRepository.save(entity);

        return AddHospitalizationDailyNoteResponseDto.builder()
                .id(entity.getId()).build();
    }

    @Override
    public UpdateHospitalizationDailyNoteResponseDto updateHospitalizationDailyNote(UpdateHospitalizationDailyNoteRequestDto requestDto) {
        HospitalizationDailyNote entity =
                hospitalizationDailyNoteRepository.findByIdForUpdate(requestDto.getId())
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_ALREADY_EXISTS));

        User veterinarian =
                userRepository.findById(requestDto.getVeterinarianId())
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.USER_NOT_FOUND));

        entity.setVeterinarian(veterinarian);
        entity.setRecordDateTime(requestDto.getRecordDateTime());
        entity.setTemperature(requestDto.getTemperature());
        entity.setPulse(requestDto.getPulse());
        entity.setRespiration(requestDto.getRespiration());
        entity.setWeight(requestDto.getWeight());
        entity.setAppetite(requestDto.getAppetite());
        entity.setUrination(requestDto.getUrination());
        entity.setDefecation(requestDto.getDefecation());
        entity.setMedication(requestDto.getMedication());
        entity.setNote(requestDto.getNote());

        hospitalizationDailyNoteRepository.save(entity);

        return UpdateHospitalizationDailyNoteResponseDto.builder()
                .id(entity.getId())
                .build();
    }

    @Override
    public DeleteHospitalizationDailyNoteResponseDto deleteHospitalizationDailyNote(DeleteHospitalizationDailyNoteRequestDto requestDto) {
        HospitalizationDailyNote entity =
                hospitalizationDailyNoteRepository.findByIdForUpdate(requestDto.getId())
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.USER_NOT_FOUND));

        entity.setDeletedAt(LocalDateTime.now());
        entity.setDeleted(true);

        hospitalizationDailyNoteRepository.save(entity);
        return new DeleteHospitalizationDailyNoteResponseDto();
    }
}
