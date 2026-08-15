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
public class CompleteSaleResponseDto {

    private Long saleId;

    private String message;

    }
