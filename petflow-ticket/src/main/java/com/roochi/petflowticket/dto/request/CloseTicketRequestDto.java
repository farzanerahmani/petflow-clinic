package com.roochi.petflowticket.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class CloseTicketRequestDto {

    @NotNull
    private Long ticketId;

    @NotBlank
    @Size(max = 2000)
    private String resolution;

    @Size(max = 30)
    private String resolvedInVersion;
}
