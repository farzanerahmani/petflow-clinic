package com.roochi.petflowclinic.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Getter
@AllArgsConstructor
public enum WorkingDay {
    SATURDAY("Saturday"),
    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday");

    private final String title;
}
