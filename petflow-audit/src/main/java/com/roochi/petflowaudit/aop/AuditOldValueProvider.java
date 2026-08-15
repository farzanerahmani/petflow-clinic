package com.roochi.petflowaudit.aop;

/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */
public interface AuditOldValueProvider {

    String getOldValue(
            Object[] args
    );
}
