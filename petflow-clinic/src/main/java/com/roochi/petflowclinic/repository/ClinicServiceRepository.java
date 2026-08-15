package com.roochi.petflowclinic.repository;

import com.roochi.petflowshared.repository.BaseRepository;
import com.roochi.petflowclinic.entity.ClinicService;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Repository
public interface ClinicServiceRepository extends BaseRepository<ClinicService, Long> {

    List<ClinicService> findByClinicId(Long clinicId);

    List<ClinicService> findByServiceTypeId(Long serviceTypeId);

    boolean existsByClinicIdAndServiceTypeId(Long clinicId,Long serviceTypeId);
}
