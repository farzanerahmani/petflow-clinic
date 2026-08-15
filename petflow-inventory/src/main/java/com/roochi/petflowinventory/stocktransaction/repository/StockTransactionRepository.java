package com.roochi.petflowinventory.stocktransaction.repository;

import com.roochi.petflowinventory.stocktransaction.entity.StockTransaction;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockReferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Repository
public interface StockTransactionRepository
        extends JpaRepository<StockTransaction, Long> {


    @Query("""
            select st
            from StockTransaction st
            where st.stock.id = :stockId
            order by st.createdAt desc
            """)
    List<StockTransaction> findAllByStockId(
            @Param("stockId") Long stockId
    );


    @Query("""
            select st
            from StockTransaction st
            where st.referenceType = :referenceType
              and st.referenceId = :referenceId
            order by st.createdAt
            """)
    List<StockTransaction> findAllByReference(
            @Param("referenceType") StockReferenceType referenceType,
            @Param("referenceId") Long referenceId
    );


    @Query("""
            select st
            from StockTransaction st
            where st.stock.drug.id = :drugId
            order by st.createdAt desc
            """)
    List<StockTransaction> findAllByDrugId(
            @Param("drugId") Long drugId
    );


    @Query("""
            select st
            from StockTransaction st
            where st.stock.warehouse.id = :warehouseId
            order by st.createdAt desc
            """)
    List<StockTransaction> findAllByWarehouseId(
            @Param("warehouseId") Long warehouseId
    );
}