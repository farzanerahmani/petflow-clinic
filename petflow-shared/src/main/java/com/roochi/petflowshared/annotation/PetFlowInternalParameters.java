package com.roochi.petflowshared.annotation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

import java.lang.annotation.*;

/**
 * @author farzane.rahmani
 * @created 6/3/2026
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Parameters({
        @Parameter(in = ParameterIn.HEADER, name = "x-request-id", description = "${PetFlowController.requestId}"),
        @Parameter(in = ParameterIn.HEADER, name = "x-forwarded-for", description = "${PetFlowController.xForwardedFor}"),
        @Parameter(in = ParameterIn.HEADER, name = "x-remote-client-ip", description = "${PetFlowController.clientIp}"),
})
public @interface PetFlowInternalParameters {
}
