package com.roochi.petflowvisit.labtest.mapper;

import com.roochi.petflowshared.mapper.BaseMapper;
import com.roochi.petflowvisit.dto.cmmon.LabTestDto;
import com.roochi.petflowvisit.dto.request.labtest.AddLabTestRequestDto;
import com.roochi.petflowvisit.dto.request.labtest.UpdateLabTestRequestDto;
import com.roochi.petflowvisit.labtest.entity.LabTest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */

@Mapper(componentModel = "spring")
public interface LabTestMapper extends BaseMapper<LabTest, LabTestDto> {

    LabTest toEntity(AddLabTestRequestDto requestDto);

    LabTest toEntity(UpdateLabTestRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateLabTestRequestDto requestDto,
                @MappingTarget LabTest labTest);

    LabTestDto toLabTestDto(LabTest labTest);
}
