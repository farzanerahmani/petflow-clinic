package com.roochi.petflowvisit.drug.mapper;

import com.roochi.petflowshared.mapper.BaseMapper;
import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.dto.cmmon.DrugDto;
import com.roochi.petflowvisit.dto.cmmon.VisitDto;
import com.roochi.petflowvisit.dto.request.drug.AddDrugRequestDto;
import com.roochi.petflowvisit.dto.request.drug.UpdateDrugRequestDto;
import com.roochi.petflowvisit.dto.request.visit.AddVisitRequestDto;
import com.roochi.petflowvisit.dto.request.visit.UpdateVisitRequestDto;
import com.roochi.petflowvisit.visit.entity.Visit;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */

@Mapper(componentModel = "spring")
public interface DrugMapper extends BaseMapper<Drug, DrugDto> {

    Drug toEntity(AddDrugRequestDto requestDto);

    Drug toEntity(UpdateDrugRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateDrugRequestDto requestDto,
                @MappingTarget Drug drug);

    DrugDto toDrugDto(Drug drug);
}
