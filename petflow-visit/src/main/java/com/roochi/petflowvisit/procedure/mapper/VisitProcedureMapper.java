package com.roochi.petflowvisit.procedure.mapper;

import com.roochi.petflowvisit.dto.cmmon.VisitProcedureDto;
import com.roochi.petflowvisit.dto.response.procedure.VisitProcedureResponseDto;
import com.roochi.petflowvisit.procedure.entity.VisitProcedure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */


@Mapper(componentModel = "spring")
public interface VisitProcedureMapper {

    @Mapping(target = "visitId", source = "visit.id")
    @Mapping(target = "procedureId", source = "procedure.id")
    @Mapping(target = "procedureName", source = "procedure.name")

    @Mapping(target = "performedById", source = "performedBy.id")
    @Mapping(target = "performedByName", source = "performedBy.fullName")

    @Mapping(target = "assistantId", source = "assistant.id")
    @Mapping(target = "assistantName", source = "assistant.fullName")
    VisitProcedureResponseDto toResponseDto(VisitProcedure entity);

    @Mapping(target = "procedureName", source = "procedure.name")
    @Mapping(target = "performedByName", source = "performedBy.fullName")
    VisitProcedureDto toVisitProcedureDto(VisitProcedure entity);

    List<VisitProcedureResponseDto> toResponseDtos(List<VisitProcedure> entities);

    List<VisitProcedureDto> toVisitProcedureDtos(List<VisitProcedure> entities);
}
