package com.roochi.petflowpet.specification;

import com.roochi.petflowpet.dto.request.GetAllPetsRequestDto;
import com.roochi.petflowpet.dto.request.SearchPetRequestDto;
import com.roochi.petflowpet.entity.Pet;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/8/2026
 */
public final class PetSpecification {
    private PetSpecification() {
    }

    public static Specification<Pet> search(GetAllPetsRequestDto requestDto,List<Long> petIds) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.isFalse(root.get("deleted")));

            predicates.add(root.get("id").in(petIds));

            if (requestDto.getName() != null && !requestDto.getName().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" +
                        requestDto.getName().toLowerCase() + "%"));
            }
            if (requestDto.getMicrochipId() != null && !requestDto.getMicrochipId().isBlank()) {
                predicates.add(cb.equal(root.get("microchipId"),
                        requestDto.getMicrochipId()));
            }
            if (requestDto.getSpecies() != null) {
                predicates.add(cb.equal(root.get("species"),
                        requestDto.getSpecies()));
            }
            if (requestDto.getBreed() != null) {
                predicates.add(cb.equal(root.get("breed"),
                        requestDto.getBreed()));
            }

            if (requestDto.getOwnerId() != null) {
                predicates.add(cb.equal(root.get("ownerId"),
                        requestDto.getOwnerId()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
