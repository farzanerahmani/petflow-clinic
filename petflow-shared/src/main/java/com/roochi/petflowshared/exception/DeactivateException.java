package com.roochi.petflowshared.exception;

import com.roochi.petflowshared.exception.constants.ErrorCode;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public class DeactivateException extends BusinessException {

    public DeactivateException(ErrorCode errorCode) {
        super(errorCode);
    }
}
