package com.roochi.petflowpet.repository;


import com.roochi.petflowpet.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 6/2/2026
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Long>, JpaSpecificationExecutor<Pet> {

    boolean existsByMicrochipIdAndDeletedFalse(String microchipId);

    Optional<Pet> findByIdAndClinicId(Long petId, Long clinicId);


    @EntityGraph(attributePaths = {"pet"})
    Page<Pet> findAllByDeletedFalse(Pageable pageable);

    Page<Pet> findAll(Specification<Pet> specification, Pageable pageable);

}
