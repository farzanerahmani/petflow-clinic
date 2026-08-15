package com.roochi.petflowclinic.dto.request;

import com.roochi.petflowshared.enums.ClinicType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Getter
@Setter
public class CreateClinicRequestDto {
    @NotBlank
    @Size(max = 150)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String code;

    @NotNull
    private ClinicType clinicType;

    @Size(max = 500)
    private String description;
}
