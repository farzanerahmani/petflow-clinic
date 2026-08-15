package com.roochi.petflowshared.exception;

import com.roochi.petflowshared.exception.constants.ErrorCode;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
