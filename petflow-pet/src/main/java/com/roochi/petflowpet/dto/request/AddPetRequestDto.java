package com.roochi.petflowpet.dto.request;

import com.roochi.petflowpet.entity.enumeration.Gender;
import com.roochi.petflowpet.entity.enumeration.PetSpecies;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 6/2/2026
 */
@Data
public class AddPetRequestDto {
    @NotNull
    private Long ownerId;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    private PetSpecies petSpecies;

    @Size(max = 100)
    private String breed;

    @NotNull
    private Gender gender;

    @DecimalMin("0.0")
    private BigDecimal weight;

    private LocalDate birthDate;

    @Size(max = 100)
    private String microchipId;
}
