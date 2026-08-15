package com.roochi.petflowshared.annotation;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

/**
 * @author farzane.rahmani
 * @created 6/3/2026
 */
@Data
@Schema(name = "HttpServerException")
public class HttpServerException {

    @Schema(description = "${HttpServerException.timestamp}", example = "1653398406549")
    private long timestamp;
    @Schema(description = "${HttpServerException.errorType}", example = "RtgsException")
    private String errorType;
    @Schema(description = "${HttpServerException.errorCode}")
    private String errorCode;
    @Schema(description = "${HttpServerException.message}")
    private String message;
    @Schema(description = "${HttpServerException.errorParam}", implementation = Object.class,
            example = "{\"param1\":\"value1\"}")
    private Map<String, Object> errorParam;
}
