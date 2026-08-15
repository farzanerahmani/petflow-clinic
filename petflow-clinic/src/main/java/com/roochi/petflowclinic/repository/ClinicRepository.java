package com.roochi.petflowclinic.repository;

import com.roochi.petflowshared.repository.BaseRepository;
import com.roochi.petflowclinic.entity.Clinic;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Repository
public interface ClinicRepository extends BaseRepository<Clinic,Long> {

    Optional<Clinic> findByCode(String code);

    boolean existsByCode(String code);
}
