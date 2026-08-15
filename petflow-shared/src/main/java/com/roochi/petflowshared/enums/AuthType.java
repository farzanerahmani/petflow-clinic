package com.roochi.petflowshared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Getter
@AllArgsConstructor
public enum AuthType {
    PASSWORD("Username & Password"),
    OTP("One time password"),
    GOOGLE("Google"),
    APPLE("Apple"),
    MICROSOFT("Microsoft");

    private final String title;
}
