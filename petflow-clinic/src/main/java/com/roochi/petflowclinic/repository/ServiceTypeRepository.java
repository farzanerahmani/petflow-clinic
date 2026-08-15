package com.roochi.petflowclinic.repository;

import com.roochi.petflowshared.repository.BaseRepository;
import com.roochi.petflowclinic.entity.ServiceType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Repository
public interface ServiceTypeRepository extends BaseRepository<ServiceType,Long> {
    Optional<ServiceType> findByCode(String code);

    boolean existsByCode(String code);
}
