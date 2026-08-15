package com.roochi.petflowvisit.medicalrecord.mapper;

import com.roochi.petflowshared.mapper.BaseMapper;
import com.roochi.petflowvisit.dto.cmmon.MedicalRecordDto;
import com.roochi.petflowvisit.dto.request.medicalrecord.AddMedicalRecordRequestDto;
import com.roochi.petflowvisit.dto.request.medicalrecord.UpdateMedicalRecordRequestDto;
import com.roochi.petflowvisit.medicalrecord.entity.MedicalRecord;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
@Mapper(componentModel = "spring")
public interface MedicalRecordMapper extends BaseMapper<MedicalRecord, MedicalRecordDto> {

    MedicalRecord toEntity(AddMedicalRecordRequestDto requestDto);

    MedicalRecord toEntity(UpdateMedicalRecordRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateMedicalRecordRequestDto requestDto,
                @MappingTarget MedicalRecord medicalRecord);

    MedicalRecordDto toMedicalRecordDto(MedicalRecord medicalRecord);
}
