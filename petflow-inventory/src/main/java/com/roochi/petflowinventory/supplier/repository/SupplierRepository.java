package com.roochi.petflowinventory.supplier.repository;

import com.roochi.petflowinventory.supplier.entity.Supplier;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */


@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Override
    @Query("""
            select s from Supplier s where s.id = :id and s.deleted = false
             """)
    Optional<Supplier> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(""" 
            select s from Supplier s where s.id = :id and s.deleted = false
             """)
    Optional<Supplier> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select s from Supplier s where s.code = :code and s.deleted = false 
            """)
    Optional<Supplier> findByCode(@Param("code") String code);

    @Query(""" 
            select s from Supplier s where s.deleted = false 
            and (:code is null or lower(s.code) 
            like lower(concat('%', :code, '%'))) 
            and (:name is null or lower(s.name) 
            like lower(concat('%', :name, '%')))
             and (:active is null or s.active = :active) order by s.name 
             """)
    Page<Supplier> search(@Param("code") String code,
                          @Param("name") String name,
                          @Param("active") Boolean active,
                          Pageable pageable);
}
