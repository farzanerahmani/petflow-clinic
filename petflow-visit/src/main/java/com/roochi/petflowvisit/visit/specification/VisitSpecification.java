package com.roochi.petflowvisit.visit.specification;

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
public class VisitSpecification {
    public static Specification<Visit> filter(Long clinicId, GetAllVisitsRequestDto requestDto){
        return ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("clinicId"),clinicId));

            if(requestDto.getPetId()!=null){
               predicates.add(cb.equal(root.get("petId"),requestDto.getPetId()));
            }

            if(requestDto.getDoctorUserId()!=null){
                predicates.add(cb.equal(root.get("doctorUserId"),requestDto.getDoctorUserId()));
            }

            if(requestDto.getStatus()!=null){
                predicates.add(cb.equal(root.get("status"),requestDto.getStatus()));
            }

            if(requestDto.getType()!=null){
                predicates.add(cb.equal(root.get("type"),requestDto.getType()));
            }

            if(requestDto.getFromDate()!=null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("visitDate"),
                        requestDto.getFromDate().atStartOfDay()));
            }

            if(requestDto.getToDate()!=null){
                predicates.add(cb.lessThanOrEqualTo(root.get("visitDate"),
                        requestDto.getToDate().atTime(LocalTime.MAX)));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
