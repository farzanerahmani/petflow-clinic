package com.roochi.petflowvisit.vaccination.mapper;

import com.roochi.petflowvisit.dto.cmmon.VaccinationDto;
import com.roochi.petflowvisit.dto.response.vaccination.VaccinationResponseDto;
import com.roochi.petflowvisit.vaccination.entity.Vaccination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
@Mapper(componentModel = "spring")
public interface VaccinationMapper {

    @Mapping(target = "visitId", source = "visit.id")
    @Mapping(target = "vaccineId", source = "vaccine.id")
    @Mapping(target = "vaccineCode", source = "vaccine.code")
    @Mapping(target = "vaccineName", source = "vaccine.name")
    VaccinationResponseDto toResponseDto(Vaccination vaccination);

    @Mapping(target = "vaccineName", source = "vaccine.name")
    VaccinationDto toVaccinationDto(Vaccination vaccination);

    List<VaccinationDto> toVaccinationDtos(List<Vaccination> vaccinations);

}
