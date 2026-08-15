package com.roochi.petflowvisit.vaccine.specification;

import com.roochi.petflowvisit.dto.request.vaccine.SearchVaccineRequestDto;
import com.roochi.petflowvisit.vaccine.entity.Vaccine;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
public class VaccineSpecification {
    public static Specification<Vaccine> filter(SearchVaccineRequestDto requestDto) {
        return ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (requestDto.getDisease() != null) {
                predicates.add(cb.equal(root.get("disease"), requestDto.getDisease()));
            }

            if (requestDto.getName() != null) {
                predicates.add(cb.equal(root.get("name"), requestDto.getName()));
            }

            if (requestDto.getCode() != null) {
                predicates.add(cb.equal(root.get("code"), requestDto.getCode()));
            }

            if (requestDto.getActive() != null) {
                predicates.add(cb.equal(root.get("active"), requestDto.getActive()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
