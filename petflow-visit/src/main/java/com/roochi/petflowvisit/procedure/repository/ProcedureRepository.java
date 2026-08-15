package com.roochi.petflowvisit.procedure.repository;

import com.roochi.petflowvisit.procedure.entity.Procedure;
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
 * @created 7/23/2026
 */


@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {

    @Query("""
            select p
            from Procedure p
            where p.deleted = false
            """)
    List<Procedure> findAll();

    @Query("""
            select p
            from Procedure p
            where p.id = :id
              and p.deleted = false
            """)
    Optional<Procedure> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select p
            from Procedure p
            where p.id = :id
              and p.deleted = false
            """)
    Optional<Procedure> findByIdForUpdate(@Param("id") Long id);

    boolean existsByCodeAndDeletedFalse(String code);

    @Query("""
            select p
            from Procedure p
            where p.deleted = false
              and (:code is null or lower(p.code) like lower(concat('%', :code, '%')))
              and (:name is null or lower(p.name) like lower(concat('%', :name, '%')))
              and (:active is null or p.active = :active)
            order by p.name
            """)
    Page<Procedure> search(
            @Param("code") String code,
            @Param("name") String name,
            @Param("active") Boolean active,
            Pageable pageable);

}
