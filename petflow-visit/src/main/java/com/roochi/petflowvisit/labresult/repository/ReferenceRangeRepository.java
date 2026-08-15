package com.roochi.petflowvisit.labresult.repository;

import com.roochi.petflowvisit.labresult.entity.ReferenceRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Repository
public interface ReferenceRangeRepository extends JpaRepository<ReferenceRange, Long> {

    @Query("""
            select r
            from ReferenceRange r
            where r.labTestParameter.id = :parameterId
              and r.deleted = false
              and r.active = true
            """)
    List<ReferenceRange> findByParameterId(@Param("parameterId") Long parameterId);

}
