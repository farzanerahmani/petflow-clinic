package com.roochi.petflowaudit.aop;

import com.roochi.petflowaudit.entity.enums.AuditAction;
import com.roochi.petflowaudit.entity.enums.AuditEntityType;

import java.lang.annotation.*;

/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auditable {

    AuditAction action();

    AuditEntityType entityType();

    String description() default "";

    /**
     * نام پارامتر حاوی entity id
     */
    String entityIdParam() default "";

    /**
     * آیا result متد به عنوان newValue ذخیره شود؟
     */
    boolean captureResult() default true;

    /**
     * آیا oldValue ثبت شود؟
     */
    boolean captureOldValue() default false;
}