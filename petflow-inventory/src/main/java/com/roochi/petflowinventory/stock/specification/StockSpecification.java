package com.roochi.petflowinventory.stock.specification;

import com.roochi.petflowinventory.stock.dto.request.SearchStockRequestDto;
import com.roochi.petflowinventory.stock.entity.Stock;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/1/2026
 */
public final class StockSpecification {

    private StockSpecification() {
    }

    public static Specification<Stock> search(
            SearchStockRequestDto request) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.isFalse(root.get("deleted")));

            predicates.add(cb.isTrue(root.get("active")));

            if (request.getWarehouseId() != null) {
                predicates.add(
                        cb.equal(
                                root.get("warehouse").get("id"),
                                request.getWarehouseId()));
            }

            if (request.getDrugId() != null) {
                predicates.add(
                        cb.equal(
                                root.get("drug").get("id"),
                                request.getDrugId()));
            }

            if (StringUtils.hasText(request.getBatchNumber())) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("batchNumber")),
                                "%" + request.getBatchNumber().toLowerCase() + "%"));
            }

            if (Boolean.TRUE.equals(request.getOnlyAvailable())) {
                predicates.add(
                        cb.greaterThan(
                                root.get("quantity"),
                                root.get("reservedQuantity")));
            }

            query.orderBy(
                    cb.asc(root.get("drug").get("brandName")),
                    cb.asc(root.get("expirationDate")));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
