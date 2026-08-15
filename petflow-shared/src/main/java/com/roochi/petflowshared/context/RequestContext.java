package com.roochi.petflowshared.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestContext {

    private Long branchId;
    private String language;
    private String timeZone;
    private String appVersion;
}
