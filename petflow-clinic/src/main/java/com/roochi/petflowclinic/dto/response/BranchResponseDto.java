package com.roochi.petflowclinic.dto.response;

import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchResponseDto {
    private Long id;

    private Long clinicId;

    private String name;

    private String code;

    private String phone;

    private String email;

    private String address;

    private Boolean mainBranch;

    private Boolean active;
}
