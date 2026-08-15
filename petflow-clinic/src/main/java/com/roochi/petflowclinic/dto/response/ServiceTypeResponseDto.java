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
public class ServiceTypeResponseDto {
    private Long id;

    private String title;

    private String code;
}
