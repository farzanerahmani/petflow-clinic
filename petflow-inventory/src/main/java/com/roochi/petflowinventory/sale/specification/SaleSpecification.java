package com.roochi.petflowinventory.sale.specification;

import com.roochi.petflowinventory.sale.dto.request.SaleSearchRequestDto;
import com.roochi.petflowinventory.sale.entity.Sale;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author farzane.rahmani
 * @created 8/3/2026
 */


public class SaleSpecification {

    private SaleSpecification() {
    }

    public static Specification<Sale> search(
            SaleSearchRequestDto request) {

        return (root, query, cb) -> {

            var predicate = cb.conjunction();

            if (request.getSaleNumber() != null &&
                    !request.getSaleNumber().isBlank()) {

                predicate.getExpressions().add(
                        cb.like(
                                cb.lower(root.get("saleNumber")),
                                "%" + request.getSaleNumber().toLowerCase() + "%"
                        )
                );
            }

            if (request.getWarehouseId() != null) {

                predicate.getExpressions().add(
                        cb.equal(
                                root.get("warehouse").get("id"),
                                request.getWarehouseId()
                        )
                );
            }

            if (request.getCustomerId() != null) {

                predicate.getExpressions().add(
                        cb.equal(
                                root.get("customer").get("id"),
                                request.getCustomerId()
                        )
                );
            }

            if (request.getStatus() != null) {

                predicate.getExpressions().add(
                        cb.equal(
                                root.get("status"),
                                request.getStatus()
                        )
                );
            }

            if (request.getFromDate() != null) {

                predicate.getExpressions().add(
                        cb.greaterThanOrEqualTo(
                                root.get("saleDate"),
                                request.getFromDate()
                        )
                );
            }

            if (request.getToDate() != null) {

                predicate.getExpressions().add(
                        cb.lessThanOrEqualTo(
                                root.get("saleDate"),
                                request.getToDate()
                        )
                );
            }

            return predicate;

        };

    }

}
