package com.roochi.petflowidentity.auth.dto.request;

import com.roochi.petflowshared.annotation.Mobile;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/7/2026
 */
@Data
public class SetPinRequestDto{

    @NotEmpty
    @Mobile
    private String mobile;
    @NotEmpty
    private String pin;
}
