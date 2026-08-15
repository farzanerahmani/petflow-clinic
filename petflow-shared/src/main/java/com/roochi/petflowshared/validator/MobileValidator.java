package com.roochi.petflowshared.validator;

import java.util.regex.Pattern;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public final class MobileValidator {
    private static final Pattern MOBILE_PATTERN=Pattern.compile("^09\\d{9}$");

    private MobileValidator(){}

    public static boolean isValid(String mobile){
        return mobile!=null
                &&
                MOBILE_PATTERN.matcher(mobile).matches();
    }
}

