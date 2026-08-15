package com.roochi.petflowinventory.warehouse.repository;

import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */



@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Override
    @Query("""
            select w
            from Warehouse w
            where w.id = :id
              and w.deleted = false
            """)
    Optional<Warehouse> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select w
            from Warehouse w
            where w.id = :id
              and w.deleted = false
            """)
    Optional<Warehouse> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select w
            from Warehouse w
            where w.code = :code
              and w.deleted = false
            """)
    Optional<Warehouse> findByCode(@Param("code") String code);

    @Query("""
            select w
            from Warehouse w
            where w.defaultWarehouse = true
              and w.deleted = false
            """)
    Optional<Warehouse> findDefaultWarehouse();

    @Query("""
            select w
            from Warehouse w
            where w.deleted = false
              and (:code is null or lower(w.code) like lower(concat('%', :code, '%')))
              and (:name is null or lower(w.name) like lower(concat('%', :name, '%')))
              and (:active is null or w.active = :active)
            order by w.name
            """)
    Page<Warehouse> search(
            @Param("code") String code,
            @Param("name") String name,
            @Param("active") Boolean active,
            Pageable pageable);

}

