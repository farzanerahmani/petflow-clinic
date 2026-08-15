package com.roochi.petflowaudit.aop;

/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */

public final class AuditContextHolder {

    private static final ThreadLocal<String>
            OLD_VALUE = new ThreadLocal<>();

    private AuditContextHolder() {
    }

    public static void setOldValue(
            String oldValue
    ) {

        OLD_VALUE.set(oldValue);
    }

    public static String getOldValue() {

        return OLD_VALUE.get();
    }

    public static void clear() {

        OLD_VALUE.remove();
    }
}
