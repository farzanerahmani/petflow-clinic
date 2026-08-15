package com.roochi.petflowclinic.repository;

import com.roochi.petflowshared.repository.BaseRepository;
import com.roochi.petflowclinic.entity.ClinicBranchBusinessHour;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Repository
public interface ClinicBusinessHourRepository extends BaseRepository<ClinicBranchBusinessHour,Long> {

    List<ClinicBranchBusinessHour> findByBranchId(Long branchId);
}
