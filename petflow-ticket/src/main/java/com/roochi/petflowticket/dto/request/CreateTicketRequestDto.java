package com.roochi.petflowticket.dto.request;

import com.roochi.petflowticket.entity.enums.*;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 8/10/2026
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketRequestDto {

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    @Size(max = 5000)
    private String description;

    @NotNull
    private TicketCategory category;

    @NotNull
    private TicketPriority priority;

    @NotNull
    private TicketModule module;

    private Long referenceId;
}
