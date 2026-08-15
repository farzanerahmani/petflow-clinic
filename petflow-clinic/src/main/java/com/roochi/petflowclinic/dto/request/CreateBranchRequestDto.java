package com.roochi.petflowclinic.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBranchRequestDto {

    @NotNull
    private Long clinicId;

    @NotBlank
    private String name;

    private String code;

    private String phone;

    private String email;

    private String address;

    private Double latitude;
    private Double longitude;

    private Boolean mainBranch;
}
