package com.roochi.petflowvisit.imaging.repository;

import com.roochi.petflowvisit.imaging.entity.ImagingService;
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
 * @created 7/22/2026
 */
@Repository
public interface ImagingServiceRepository extends JpaRepository<ImagingService, Long> {

    @Query("""
            select i
            from ImagingService i
            where i.deleted = false
            """)
    List<ImagingService> findAll();

    @Query("""
            select i
            from ImagingService i
            where i.id=:id
            and i.deleted=false
            """)
    Optional<ImagingService> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select i
            from ImagingService i
            where i.id=:id
            and i.deleted=false
            """)
    Optional<ImagingService> findByIdForUpdate(@Param("id") Long id);

    boolean existsByCodeAndDeletedFalse(String code);

    @Query("""
        select i
        from ImagingService i
        where i.deleted = false
          and (:code is null or lower(i.code) like lower(concat('%', :code, '%')))
          and (:name is null or lower(i.name) like lower(concat('%', :name, '%')))
          and (:active is null or i.active = :active)
        order by i.name
        """)
    Page<ImagingService> search(
            @Param("code") String code,
            @Param("name") String name,
            @Param("active") Boolean active,
            Pageable pageable);
}
