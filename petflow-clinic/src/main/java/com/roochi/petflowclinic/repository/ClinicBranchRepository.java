package com.roochi.petflowclinic.repository;

import com.roochi.petflowshared.repository.BaseRepository;
import com.roochi.petflowclinic.entity.ClinicBranch;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Repository
public interface ClinicBranchRepository extends BaseRepository<ClinicBranch,Long> {

    List<ClinicBranch> findByClinicId(Long clinicId);
}
