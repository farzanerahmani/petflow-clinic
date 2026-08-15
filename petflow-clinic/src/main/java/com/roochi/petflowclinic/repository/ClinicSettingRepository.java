package com.roochi.petflowclinic.repository;

import com.roochi.petflowshared.repository.BaseRepository;
import com.roochi.petflowclinic.entity.ClinicSetting;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Repository
public interface ClinicSettingRepository extends BaseRepository<ClinicSetting,Long> {

    Optional<ClinicSetting> findByClinicId(Long clinicId);
}
