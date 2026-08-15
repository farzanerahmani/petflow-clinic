package com.roochi.petflowinventory.supplier.dto.response;

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
public class SupplierResponseDto {
    private Long id;
    private String code;
    private String name;
    private String contactPerson;
    private String phoneNumber;
    private String email;
    private String address;
    private String description;
    private Boolean active;
}
