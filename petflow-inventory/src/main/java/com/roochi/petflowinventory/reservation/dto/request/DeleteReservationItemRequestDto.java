package com.roochi.petflowinventory.reservation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 8/5/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteReservationItemRequestDto {

    @NotNull
    private Long id;

}
