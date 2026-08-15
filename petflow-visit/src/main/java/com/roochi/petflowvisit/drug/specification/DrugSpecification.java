package com.roochi.petflowvisit.drug.specification;

import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.dto.request.drug.SearchDrugRequestDto;
import com.roochi.petflowvisit.dto.request.visit.GetAllVisitsRequestDto;
import com.roochi.petflowvisit.visit.entity.Visit;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
public class DrugSpecification {
    public static Specification<Drug> filter(SearchDrugRequestDto requestDto) {
        return ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (requestDto.getBrandName() != null) {
                predicates.add(cb.equal(root.get("brandName"), requestDto.getBrandName()));
            }

            if (requestDto.getGenericName() != null) {
                predicates.add(cb.equal(root.get("genericName"), requestDto.getGenericName()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
