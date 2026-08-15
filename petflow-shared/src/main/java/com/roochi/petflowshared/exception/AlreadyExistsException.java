package com.roochi.petflowshared.exception;

import com.roochi.petflowshared.exception.constants.ErrorCode;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
public class AlreadyExistsException extends BusinessException {

    public AlreadyExistsException(ErrorCode errorCode) {
        super(errorCode);
    }
}
