package com.roochi.petflowclinic.repository;

import com.roochi.petflowshared.repository.BaseRepository;
import com.roochi.petflowclinic.entity.ClinicContact;
import com.roochi.petflowclinic.enumeration.ContactType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Repository
public interface ClinicContactRepository extends BaseRepository<ClinicContact,Long> {

    List<ClinicContact> findByClinicId(Long clinicId);

    List<ClinicContact> findByClinicIdAndType(Long clinicId, ContactType type);
}
