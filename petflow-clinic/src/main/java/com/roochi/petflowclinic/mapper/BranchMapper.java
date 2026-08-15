package com.roochi.petflowclinic.mapper;

import com.roochi.petflowclinic.dto.request.CreateBranchRequestDto;
import com.roochi.petflowclinic.dto.response.BranchResponseDto;
import com.roochi.petflowclinic.entity.ClinicBranch;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchMapper {
    ClinicBranch toEntity(CreateBranchRequestDto requestDto);

    BranchResponseDto toResponse(ClinicBranch branch);

    List<BranchResponseDto> toResponse(List<ClinicBranch> branches);
}
