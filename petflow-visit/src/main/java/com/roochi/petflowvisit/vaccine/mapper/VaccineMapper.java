package com.roochi.petflowvisit.vaccine.mapper;

import com.roochi.petflowshared.mapper.BaseMapper;
import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.dto.cmmon.DrugDto;
import com.roochi.petflowvisit.dto.cmmon.VaccineDto;
import com.roochi.petflowvisit.dto.request.drug.AddDrugRequestDto;
import com.roochi.petflowvisit.dto.request.drug.UpdateDrugRequestDto;
import com.roochi.petflowvisit.dto.request.vaccine.AddVaccineRequestDto;
import com.roochi.petflowvisit.dto.request.vaccine.UpdateVaccineRequestDto;
import com.roochi.petflowvisit.vaccine.entity.Vaccine;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */

@Mapper(componentModel = "spring")
public interface VaccineMapper extends BaseMapper<Vaccine, VaccineDto> {

    Vaccine toEntity(AddVaccineRequestDto requestDto);

    Vaccine toEntity(UpdateVaccineRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateDrugRequestDto requestDto,
                @MappingTarget Vaccine vaccine);

    VaccineDto toVaccineDto(Vaccine vaccine);
}