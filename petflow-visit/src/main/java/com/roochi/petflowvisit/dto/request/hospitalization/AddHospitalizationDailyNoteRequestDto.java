package com.roochi.petflowvisit.dto.request.hospitalization;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddHospitalizationDailyNoteRequestDto {

    @NotNull
    private Long hospitalizationId;

    @NotNull
    private Long veterinarianId;

    @NotNull
    private LocalDateTime recordDateTime;

    private BigDecimal temperature;

    private Integer pulse;

    private Integer respiration;

    private BigDecimal weight;

    @Size(max = 200)
    private String appetite;

    @Size(max = 200)
    private String urination;

    @Size(max = 200)
    private String defecation;

    @Size(max = 1000)
    private String medication;

    @Size(max = 2000)
    private String note;
}
