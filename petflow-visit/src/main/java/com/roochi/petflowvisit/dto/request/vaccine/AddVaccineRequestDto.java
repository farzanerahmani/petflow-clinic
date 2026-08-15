package com.roochi.petflowvisit.dto.request.vaccine;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddVaccineRequestDto {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String manufacturer;

    private String disease;

    private String species;

    private String description;
}
