package com.roochi.petflowshared.exception;

import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.Getter;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
