package com.roochi.petflowpet.service.impl;

import com.roochi.petflowpet.dto.request.*;
import com.roochi.petflowpet.dto.response.*;
import com.roochi.petflowpet.entity.Pet;
import com.roochi.petflowpet.entity.PetClinic;
import com.roochi.petflowpet.entity.enumeration.PetStatus;
import com.roochi.petflowpet.entity.enumeration.PetClinicStatus;
import com.roochi.petflowpet.mapper.PetMapper;
import com.roochi.petflowpet.repository.PetRepository;
import com.roochi.petflowpet.repository.PetClinicRepository;
import com.roochi.petflowpet.service.command.PetCommandService;
import com.roochi.petflowshared.exception.AlreadyExistsException;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowshared.security.JwtAuthentication;
import com.roochi.petflowshared.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/8/2026
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PetCommandServiceImpl implements PetCommandService {

    private final PetRepository petRepository;

    private final PetClinicRepository petClinicRepository;
    private final SecurityUtils securityUtils;

    private final PetMapper petMapper;

    @Override
    public UpdatePetResponseDto updatePet(UpdatePetRequestDto requestDto) {

        Pet pet = petRepository.findById(requestDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        if (Boolean.TRUE.equals(pet.getDeleted()))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        if (StringUtils.hasText(requestDto.getMicrochipId()) &&
                !requestDto.getMicrochipId().equals(pet.getMicrochipId())
                && petRepository.existsByMicrochipIdAndDeletedFalse(requestDto.getMicrochipId()))
            throw new AlreadyExistsException(ErrorCode.USER_NOT_FOUND);

        pet.setName(requestDto.getName());
        pet.setSpecies(requestDto.getPetSpecies());
        pet.setBreed(requestDto.getBreed());
        pet.setGender(requestDto.getGender());
        pet.setWeight(requestDto.getWeight());
        pet.setBirthDate(requestDto.getBirthDate());
        pet.setMicrochipId(requestDto.getMicrochipId());

        petRepository.save(pet);
        var response = new UpdatePetResponseDto();
        response.setPet(petMapper.toPetDto(pet));
        return response;
    }

    @Override
    public AddPetResponseDto addPet(AddPetRequestDto requestDto) {
        if (StringUtils.hasText(requestDto.getMicrochipId())) {
            if (petRepository.existsByMicrochipIdAndDeletedFalse(requestDto.getMicrochipId()))
                throw new AlreadyExistsException(ErrorCode.USER_NOT_FOUND);
        }

        Pet pet = Pet.builder()
                .ownerId(requestDto.getOwnerId()) //todo: اعتبار سنجی petowner
                .name(requestDto.getName())
                .species(requestDto.getPetSpecies())
                .breed(requestDto.getBreed())
                .gender(requestDto.getGender())
                .weight(requestDto.getWeight())
                .birthDate(requestDto.getBirthDate())
                .microchipId(requestDto.getMicrochipId())
                .status(PetStatus.ACTIVE).build();
        petRepository.save(pet);

        PetClinic petClinic = new PetClinic();
        petClinic.setPetId(pet.getId());
        petClinic.setClinicId(securityUtils.getCurrentClinicId());
        petClinic.setJoinedAt(LocalDate.now());
        petClinic.setStatus(PetClinicStatus.ACTIVE);
        petClinicRepository.save(petClinic);

        var response = new AddPetResponseDto();
        response.setPet(petMapper.toPetDto(pet));
        return response;
    }

    @Override
    public DeletePetResponseDto deletePet(DeletePetRequestDto requestDto) {

        JwtAuthentication authentication = (JwtAuthentication) SecurityContextHolder.getContext()
                .getAuthentication();
        Long clinicId = authentication.getClinicId();
        PetClinic petClinic = petClinicRepository.findByPetIdAndClinicIdAndStatus(
                requestDto.getId(), clinicId, PetClinicStatus.ACTIVE
        ).orElseThrow();

        Pet pet = petRepository.findById(requestDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        if (Boolean.TRUE.equals(pet.getDeleted()))
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        pet.setDeleted(true);
        pet.setDeletedAt(LocalDateTime.now());
        petRepository.save(pet);
        petClinic.setStatus(PetClinicStatus.INACTIVE);
        petClinic.setLeftAt(LocalDate.now());
        petClinicRepository.save(petClinic);

        return new DeletePetResponseDto();
    }

    @Override
    public ActivatePetResponseDto activatePet(ActivatePetRequestDto requestDto) {

        JwtAuthentication authentication = (JwtAuthentication)
                SecurityContextHolder.getContext().getAuthentication();

        Long clinicId = authentication.getClinicId();

        PetClinic petClinic = petClinicRepository
                .findByPetIdAndClinicId(requestDto.getPetId(), clinicId)
                .orElseThrow();

        petClinic.setStatus(PetClinicStatus.ACTIVE);
        petClinic.setJoinedAt(LocalDate.now());
        petClinic.setLeftAt(null);
        petClinicRepository.save(petClinic);
        return new ActivatePetResponseDto();
    }

    @Override
    public DeactivatePetResponseDto deactivatePet(DeactivatePetRequestDto requestDto) {

        JwtAuthentication authentication = (JwtAuthentication)
                SecurityContextHolder.getContext().getAuthentication();

        Long clinicId = authentication.getClinicId();

        PetClinic petClinic = petClinicRepository
                .findByPetIdAndClinicIdAndStatus(requestDto.getPetId(), clinicId, PetClinicStatus.ACTIVE)
                .orElseThrow();

        petClinic.setStatus(PetClinicStatus.INACTIVE);
        petClinic.setLeftAt(LocalDate.now());
        petClinicRepository.save(petClinic);


        return new DeactivatePetResponseDto();
    }

    @Override
    public ReportLostPetResponseDto reportLostPet(ReportLostPetRequestDto requestDto) {
        JwtAuthentication authentication = (JwtAuthentication)
                SecurityContextHolder.getContext().getAuthentication();

        Long clinicId = authentication.getClinicId();

        petClinicRepository
                .findByPetIdAndClinicIdAndStatus(requestDto.getPetId(), clinicId, PetClinicStatus.ACTIVE)
                .orElseThrow();

        Pet pet = petRepository.findById(requestDto.getPetId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        pet.setStatus(PetStatus.LOST);
        petRepository.save(pet);
        return  new ReportLostPetResponseDto();
    }

    @Override
    public ReportFoundPetResponseDto reportFoundPet(ReportFoundPetRequestDto requestDto) {
        JwtAuthentication authentication = (JwtAuthentication)
                SecurityContextHolder.getContext().getAuthentication();

        Long clinicId = authentication.getClinicId();

        petClinicRepository
                .findByPetIdAndClinicIdAndStatus(requestDto.getPetId(), clinicId, PetClinicStatus.ACTIVE)
                .orElseThrow();
        Pet pet = petRepository.findById(requestDto.getPetId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        pet.setStatus(PetStatus.FOUND);
        petRepository.save(pet);
        return  new ReportFoundPetResponseDto();
    }

    @Override
    public ReportDeceasedPetResponseDto reportDeceasedPet(ReportDeceasedPetRequestDto requestDto) {
        JwtAuthentication authentication = (JwtAuthentication)
                SecurityContextHolder.getContext().getAuthentication();

        Long clinicId = authentication.getClinicId();

        petClinicRepository
                .findByPetIdAndClinicIdAndStatus(requestDto.getPetId(), clinicId, PetClinicStatus.ACTIVE)
                .orElseThrow();
        Pet pet = petRepository.findById(requestDto.getPetId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        pet.setStatus(PetStatus.DECEASED);
        petRepository.save(pet);
        return  new ReportDeceasedPetResponseDto();
    }
}
