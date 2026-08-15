package com.roochi.petflowpet.service.impl;

import com.roochi.petflowpet.dto.request.GetAllPetsRequestDto;
import com.roochi.petflowpet.dto.request.GetPetByIdRequestDto;
import com.roochi.petflowpet.dto.response.GetAllPetsResponseDto;
import com.roochi.petflowpet.dto.response.GetPetByIdResponseDto;
import com.roochi.petflowpet.entity.Pet;
import com.roochi.petflowpet.entity.PetClinic;
import com.roochi.petflowpet.entity.enumeration.PetClinicStatus;
import com.roochi.petflowpet.mapper.PetMapper;
import com.roochi.petflowpet.repository.PetRepository;
import com.roochi.petflowpet.repository.PetClinicRepository;
import com.roochi.petflowpet.service.query.PetQueryService;
import com.roochi.petflowpet.specification.PetSpecification;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import com.roochi.petflowshared.security.JwtAuthentication;
import com.roochi.petflowshared.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author farzane.rahmani
 * @created 7/8/2026
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PetQueryServiceImpl implements PetQueryService {

    private final SecurityUtils securityUtils;
    private final PetRepository petRepository;
    private final PetClinicRepository petClinicRepository;

    private final PetMapper petMapper;

    @Override
    @Transactional(readOnly = true)
    public GetPetByIdResponseDto getPetById(GetPetByIdRequestDto requestDto) {

        JwtAuthentication authentication = (JwtAuthentication) SecurityContextHolder
                .getContext().getAuthentication();
        Long clinicId = authentication.getClinicId();

        petClinicRepository.findByPetIdAndClinicIdAndStatus(
                        requestDto.getId(), clinicId, PetClinicStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        Pet pet = petRepository.findByIdAndClinicId(requestDto.getId(), clinicId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        GetPetByIdResponseDto responseDto = new GetPetByIdResponseDto();
        responseDto.setPet(petMapper.toPetDto(pet));
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public GetAllPetsResponseDto getAllPets(GetAllPetsRequestDto requestDto) {
        JwtAuthentication authentication = (JwtAuthentication)
                SecurityContextHolder.getContext().getAuthentication();
        Long clinicId = authentication.getClinicId();

        Pageable pageRequest = PageRequest.of(requestDto.getPageNumber(),
                requestDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));
        List<Long> petIds = petClinicRepository.findAllByClinicIdAndStatus(
                        clinicId, PetClinicStatus.ACTIVE)
                .stream()
                .map(PetClinic::getPetId)
                .toList();

        if (petIds.isEmpty()) {
            return  PageResponseDto.emptyResponse((Supplier<GetAllPetsResponseDto>) GetAllPetsResponseDto::new);
        }

        Specification<Pet> specification =
                PetSpecification.search(requestDto, petIds);
        Page<Pet> page = petRepository.findAll(specification, pageRequest);

        GetAllPetsResponseDto response = new GetAllPetsResponseDto();
        response.setResults(page.getContent().stream().map(petMapper::toPetDto).toList());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setTotalRecords(page.getTotalElements());
        return response;
    }

}
