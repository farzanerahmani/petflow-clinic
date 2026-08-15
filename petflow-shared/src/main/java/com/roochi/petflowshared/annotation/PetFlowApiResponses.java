package com.roochi.petflowshared.annotation;

//import com.roochi.petflow.advice.dto.HttpServerException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

/**
 * @author farzane.rahmani
 * @created 6/3/2026
 */
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = " ${PetFlowController.success}"),
        @ApiResponse(
                responseCode = "500",
                content = {@Content(schema = @Schema(implementation = HttpServerException.class))},
                description = "${PetFlowController.internalError}")
})
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PetFlowApiResponses {
}
