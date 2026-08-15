package com.roochi.petflowdashboard.dto.request;

import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
@Getter
@Setter
public class DashboardRequestDto {

    private LocalDate from;

    private LocalDate to;
}
