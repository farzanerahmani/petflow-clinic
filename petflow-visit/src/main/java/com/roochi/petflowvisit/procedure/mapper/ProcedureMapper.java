package com.roochi.petflowvisit.procedure.mapper;

import com.roochi.petflowvisit.dto.cmmon.ProcedureDto;
import com.roochi.petflowvisit.dto.response.procedure.ProcedureResponseDto;
import com.roochi.petflowvisit.procedure.entity.Procedure;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/23/2026
 */


@Mapper(componentModel = "spring")
public interface ProcedureMapper {

    ProcedureResponseDto toResponseDto(Procedure entity);

    ProcedureDto toProcedureDto(Procedure entity);

    List<ProcedureResponseDto> toResponseDtos(List<Procedure> entities);

    List<ProcedureDto> toProcedureDtos(List<Procedure> entities);

}
