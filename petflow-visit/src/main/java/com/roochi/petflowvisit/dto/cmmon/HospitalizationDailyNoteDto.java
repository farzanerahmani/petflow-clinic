package com.roochi.petflowvisit.dto.cmmon;

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
public class HospitalizationDailyNoteDto {

    private Long id;

    private LocalDateTime recordDateTime;

    private BigDecimal temperature;

    private Integer pulse;

    private Integer respiration;

    private String veterinarianName;
}
