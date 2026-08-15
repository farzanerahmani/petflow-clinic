package com.roochi.petflowinventory.sale.repository;

import com.roochi.petflowinventory.sale.entity.Sale;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */


@Repository
public interface SaleRepository extends JpaRepository<Sale,Long>,
        JpaSpecificationExecutor<Sale> {

    Optional<Sale> findBySaleNumber(String saleNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select s
            from Sale s
            where s.id=:id
            """)
    Optional<Sale> findByIdForUpdate(
            @Param("id") Long id
    );

}
