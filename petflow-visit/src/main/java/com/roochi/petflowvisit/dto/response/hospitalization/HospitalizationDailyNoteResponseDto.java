package com.roochi.petflowvisit.dto.response.hospitalization;

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
public class HospitalizationDailyNoteResponseDto {

    private Long id;

    private Long hospitalizationId;

    private Long veterinarianId;

    private String veterinarianName;

    private LocalDateTime recordDateTime;

    private BigDecimal temperature;

    private Integer pulse;

    private Integer respiration;

    private BigDecimal weight;

    private String appetite;

    private String urination;

    private String defecation;

    private String medication;

    private String note;
}
