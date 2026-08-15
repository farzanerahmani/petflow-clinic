package com.roochi.petflowinventory.supplier.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/27/2026
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateSupplierRequestDto {
    @NotNull
    private Long id;
    @NotBlank
    @Size(max = 30)
    private String code;
    @NotBlank
    @Size(max = 150)
    private String name;
    @Size(max = 100)
    private String contactPerson;
    @Size(max = 20)
    private String phoneNumber;
    @Email
    @Size(max = 100)
    private String email;
    @Size(max = 300)
    private String address;
    @Size(max = 500)
    private String description;
    @NotNull
    private Boolean active;
}
