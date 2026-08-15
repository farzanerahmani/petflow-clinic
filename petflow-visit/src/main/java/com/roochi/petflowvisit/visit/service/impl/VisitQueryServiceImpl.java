package com.roochi.petflowvisit.visit.service.impl;

import com.roochi.petflowidentity.user.dto.user.GetUserByIdRequestDto;
import com.roochi.petflowidentity.user.dto.user.GetUserByIdResponseDto;
import com.roochi.petflowidentity.user.dto.user.UserDto;
import com.roochi.petflowidentity.user.facade.UserFacade;
import com.roochi.petflowpet.dto.PetDto;
import com.roochi.petflowpet.dto.request.GetPetByIdRequestDto;
import com.roochi.petflowpet.dto.response.GetPetByIdResponseDto;
import com.roochi.petflowpet.facade.PetFacade;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowshared.security.JwtAuthentication;
import com.roochi.petflowvisit.dto.cmmon.VisitDto;
import com.roochi.petflowvisit.dto.request.visit.GetAllVisitsRequestDto;
import com.roochi.petflowvisit.dto.request.visit.GetVisitByIdRequestDto;
import com.roochi.petflowvisit.dto.response.visit.GetAllVisitsResponseDto;
import com.roochi.petflowvisit.dto.response.visit.GetVisitByIdResponseDto;
import com.roochi.petflowvisit.visit.entity.Visit;
import com.roochi.petflowvisit.visit.mapper.VisitConvertor;
import com.roochi.petflowvisit.visit.mapper.VisitMapper;
import com.roochi.petflowvisit.visit.repository.VisitRepository;
import com.roochi.petflowvisit.visit.service.query.VisitQueryService;
import com.roochi.petflowvisit.visit.specification.VisitSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/10/2026
 */
@Service
@RequiredArgsConstructor
public class VisitQueryServiceImpl implements VisitQueryService {

    private final VisitRepository visitRepository;

    private final VisitMapper visitMapper;

    private final VisitConvertor visitConvertor;

    private final PetFacade petFacade;

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    @Override
    public GetVisitByIdResponseDto getVisitById(GetVisitByIdRequestDto requestDto) {
        JwtAuthentication authentication = (JwtAuthentication) SecurityContextHolder.getContext()
                .getAuthentication();
        Long clinicId = authentication.getClinicId();
        Visit visit = visitRepository.findByIdAndClinicId(requestDto.getVisitId(), clinicId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        GetPetByIdResponseDto petResponse = petFacade.getPet(
                GetPetByIdRequestDto.builder().id(requestDto.getVisitId()).build());
        GetUserByIdResponseDto doctorResponse =
                userFacade.getUserById(GetUserByIdRequestDto.builder()
                        .userId(visit.getDoctorUserId()).build());

        VisitDto visitDto = VisitDto.builder()
                .id(visit.getId())
                .visitDate(visit.getVisitDate())
                .status(visit.getStatus())
                .type(visit.getType())
                .chiefComplaint(visit.getChiefComplaint())
                .diagnosis(visit.getDiagnosis())
                .description(visit.getDescription())
                .weight(visit.getWeight())
                .temperature(visit.getTemperature())
                .heartRate(visit.getHeartRate())
                .respiratoryRate(visit.getRespiratoryRate())
                .pet(visitConvertor.convertToPetSummaryDto(petResponse.getPet()))
                .doctor(visitConvertor.convertToUserSummaryDto(
                        doctorResponse.getUser())).build();

        return GetVisitByIdResponseDto.builder()
                .visit(visitDto).build();
    }

    @Override
    @Transactional(readOnly = true)
    public GetAllVisitsResponseDto getAllVisit(GetAllVisitsRequestDto requestDto) {
        JwtAuthentication authentication = (JwtAuthentication) SecurityContextHolder.getContext()
                .getAuthentication();
        Long clinicId = authentication.getClinicId();
        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "visitDate"));

        Page<Visit> page = visitRepository.findAll(
                VisitSpecification.filter(clinicId, requestDto), pageRequest);
        List<VisitDto> visits =
                page.getContent()
                        .stream()
                        .map(visit -> {
                            PetDto pet = petFacade.getPet(GetPetByIdRequestDto.builder()
                                    .id(visit.getPetId()).build()).getPet();
                            UserDto doctor = userFacade.getUserById(GetUserByIdRequestDto.builder()
                                    .userId(visit.getDoctorUserId()).build()).getUser();

                            VisitDto dto = visitMapper.toVisitDto(visit);
                            dto.setPet(visitConvertor.convertToPetSummaryDto(pet));
                            dto.setDoctor(visitConvertor.convertToUserSummaryDto(doctor));
                            return dto;
                        }).toList();

        GetAllVisitsResponseDto response = new GetAllVisitsResponseDto();
        response.setResults(visits);
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;

        //todo : اضافه کردن getPetByIds و getUserByIds با لیست idها برای جلوگیری از n+1
    }

    @Override
    public Visit getVisitForUpdate(Long visitId) {
        return
                visitRepository.findByIdForUpdate(visitId).orElseThrow();
    }
}
