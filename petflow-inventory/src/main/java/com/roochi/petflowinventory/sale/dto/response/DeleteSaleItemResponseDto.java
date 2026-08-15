package com.roochi.petflowinventory.sale.dto.response;

import lombok.*;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteSaleItemResponseDto {

    private Long id;

    private String message;

}
