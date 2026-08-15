package com.roochi.petflowvisit.labresult.repository;

import com.roochi.petflowvisit.labresult.entity.LabResultItem;
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
public interface LabResultItemRepository extends JpaRepository<LabResultItem, Long> {

    @Query("""
            select i
            from LabResultItem i
            where i.labResult.id = :labResultId
              and i.deleted = false
            order by i.labTestParameter.name
            """)
    List<LabResultItem> findByLabResultId(@Param("labResultId") Long labResultId);

}
