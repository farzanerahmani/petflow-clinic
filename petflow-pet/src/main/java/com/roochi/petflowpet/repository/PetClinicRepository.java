package com.roochi.petflowpet.repository;

import com.roochi.petflowpet.entity.PetClinic;
import com.roochi.petflowpet.entity.enumeration.PetClinicStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/8/2026
 */
public interface PetClinicRepository extends JpaRepository<PetClinic, Long> {

    List<PetClinic> findAllByClinicIdAndStatus(Long clinicId, PetClinicStatus status);

    Optional<PetClinic> findByPetIdAndClinicIdAndStatus(Long petId, Long clinicId,
                                                        PetClinicStatus status);

    Optional<PetClinic> findByPetIdAndClinicId(Long petId, Long clinicId);

    List<PetClinic> findAllByPetId(Long petId);

    List<PetClinic> findAllByClinicId(Long clinicId);

    boolean existsByPetIdAndClinicId(Long petId, Long clinicId);
}
