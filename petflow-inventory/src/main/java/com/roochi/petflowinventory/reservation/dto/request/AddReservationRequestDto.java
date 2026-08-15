package com.roochi.petflowinventory.reservation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/5/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddReservationRequestDto {

    @NotBlank
    @Size(max = 50)
    private String reservationNumber;

    @NotNull
    private Long warehouseId;


    /**
     * اتصال به Visit یا Prescription
     */
    private Long referenceId;


    @Size(max = 50)
    private String referenceType;


    @NotNull
    private LocalDate reservationDate;


    @Size(max = 500)
    private String description;

}
