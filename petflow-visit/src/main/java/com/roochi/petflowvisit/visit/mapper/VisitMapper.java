package com.roochi.petflowvisit.visit.mapper;

import com.roochi.petflowshared.mapper.BaseMapper;
import com.roochi.petflowvisit.dto.cmmon.VisitDto;
import com.roochi.petflowvisit.dto.request.visit.AddVisitRequestDto;
import com.roochi.petflowvisit.dto.request.visit.UpdateVisitRequestDto;
import com.roochi.petflowvisit.visit.entity.Visit;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
@Mapper(componentModel = "spring")
public interface VisitMapper extends BaseMapper<Visit, VisitDto> {

    Visit toEntity(AddVisitRequestDto requestDto);

    Visit toEntity(UpdateVisitRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateVisitRequestDto requestDto,
                @MappingTarget Visit visit);

    VisitDto toVisitDto(Visit visit);


}