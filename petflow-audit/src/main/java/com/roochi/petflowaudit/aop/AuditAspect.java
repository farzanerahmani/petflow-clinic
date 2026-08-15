package com.roochi.petflowaudit.aop;

import com.roochi.petflowaudit.dto.request.CreateAuditLogRequestDto;
import com.roochi.petflowaudit.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AuditAspect {

    private final AuditService auditService;

    private final AuditContext auditContext;

    private final AuditSnapshotService snapshotService;


    @Around(
            "@annotation(com.roochi.petflowaudit.aop.Auditable)"
    )
    public Object audit(
            ProceedingJoinPoint joinPoint
    ) throws Throwable {

        Method method =
                ((MethodSignature)
                        joinPoint.getSignature())
                        .getMethod();

        Auditable auditable =
                method.getAnnotation(
                        Auditable.class
                );

        Long entityId =
                resolveEntityId(
                        joinPoint,
                        auditable
                );

        try {

            Object result =
                    joinPoint.proceed();

            String oldValue =
                    AuditContextHolder.getOldValue();

            String newValue = null;

            if (auditable.captureResult()) {

                newValue =
                        snapshotService.snapshot(
                                result
                        );
            }

            try {

                auditService.log(
                        CreateAuditLogRequestDto.builder()
                                .clinicId(
                                        auditContext.getClinicId()
                                )
                                .userId(
                                        auditContext.getUserId()
                                )
                                .action(
                                        auditable.action()
                                )
                                .entityType(
                                        auditable.entityType()
                                )
                                .entityId(entityId)
                                .description(
                                        auditable.description()
                                )
                                .oldValue(oldValue)
                                .newValue(newValue)
                                .ipAddress(
                                        auditContext.getIpAddress()
                                )
                                .userAgent(
                                        auditContext.getUserAgent()
                                )
                                .build()
                );

            } catch (Exception e) {

                log.error(
                        "Failed to create audit log",
                        e
                );
            }

            return result;

        } finally {

            AuditContextHolder.clear();
        }
    }


    private Long resolveEntityId(
            ProceedingJoinPoint joinPoint,
            Auditable auditable
    ) {

        String paramName =
                auditable.entityIdParam();

        if (paramName == null
                || paramName.isBlank()) {

            return null;
        }

        MethodSignature signature =
                (MethodSignature)
                        joinPoint.getSignature();

        String[] parameterNames =
                signature.getParameterNames();

        Object[] arguments =
                joinPoint.getArgs();

        for (int i = 0;
             i < parameterNames.length;
             i++) {

            if (paramName.equals(
                    parameterNames[i]
            )) {

                Object value =
                        arguments[i];

                if (value instanceof Number number) {

                    return number.longValue();
                }
            }
        }

        return null;
    }
}