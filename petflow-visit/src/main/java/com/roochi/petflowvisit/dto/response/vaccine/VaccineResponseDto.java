package com.roochi.petflowvisit.dto.response.vaccine;

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
public class VaccineResponseDto {

    private Long id;

    private String code;

    private String name;

    private String manufacturer;

    private String disease;

    private String species;

    private String description;

    private Boolean active;
}
