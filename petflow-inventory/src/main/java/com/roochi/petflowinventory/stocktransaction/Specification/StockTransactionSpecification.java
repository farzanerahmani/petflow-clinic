package com.roochi.petflowinventory.stocktransaction.Specification;

import com.roochi.petflowinventory.stocktransaction.dto.request.StockTransactionSearchRequestDto;
import com.roochi.petflowinventory.stocktransaction.entity.StockTransaction;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
public final class StockTransactionSpecification {


    private StockTransactionSpecification() {
    }


    public static Specification<StockTransaction> search(
            StockTransactionSearchRequestDto request) {


        return (root, query, cb) -> {


            List<Predicate> predicates = new ArrayList<>();


            if (request.getStockId() != null) {

                predicates.add(
                        cb.equal(
                                root.get("stock").get("id"),
                                request.getStockId()
                        )
                );
            }


            if (request.getDrugId() != null) {

                predicates.add(
                        cb.equal(
                                root.get("stock")
                                        .get("drug")
                                        .get("id"),
                                request.getDrugId()
                        )
                );
            }


            if (request.getWarehouseId() != null) {

                predicates.add(
                        cb.equal(
                                root.get("stock")
                                        .get("warehouse")
                                        .get("id"),
                                request.getWarehouseId()
                        )
                );
            }


            if (request.getTransactionType() != null) {

                predicates.add(
                        cb.equal(
                                root.get("transactionType"),
                                request.getTransactionType()
                        )
                );
            }


            if (request.getReferenceType() != null) {

                predicates.add(
                        cb.equal(
                                root.get("referenceType"),
                                request.getReferenceType()
                        )
                );
            }


            return cb.and(
                    predicates.toArray(new Predicate[0])
            );
        };
    }
}
