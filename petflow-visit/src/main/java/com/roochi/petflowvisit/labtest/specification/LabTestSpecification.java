package com.roochi.petflowvisit.labtest.specification;

import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.dto.request.drug.SearchDrugRequestDto;
import com.roochi.petflowvisit.dto.request.labtest.SearchLabTestRequestDto;
import com.roochi.petflowvisit.labtest.entity.LabTest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
public class LabTestSpecification {
    public static Specification<LabTest> filter(SearchLabTestRequestDto requestDto) {
        return ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

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
