package com.roochi.petflowvisit.imaging.repository;

import com.roochi.petflowvisit.imaging.entity.ImagingResult;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagingResultRepository extends JpaRepository<ImagingResult, Long> {

    @Query("""
            select ir
            from ImagingResult ir
            where ir.id = :id
              and ir.deleted = false
            """)
    Optional<ImagingResult> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select ir
            from ImagingResult ir
            where ir.id = :id
              and ir.deleted = false
            """)
    Optional<ImagingResult> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select ir
            from ImagingResult ir
            where ir.imagingRequest.id = :requestId
              and ir.deleted = false
            """)
    Optional<ImagingResult> findByImagingRequestId(
            @Param("requestId") Long requestId);

}

