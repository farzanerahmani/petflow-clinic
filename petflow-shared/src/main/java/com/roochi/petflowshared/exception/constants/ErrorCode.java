package com.roochi.petflowshared.exception.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Getter
public enum ErrorCode {

    //GENERAL
    INTERNAL_ERROR("PF-000", "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR("PF-001", "Validation failed.", HttpStatus.BAD_REQUEST),
    //AUTHENTICATION
    UNAUTHORIZED("AUTH-001", "Unautorized.", HttpStatus.UNAUTHORIZED),
    INVALID_CREDENTIALS("AUTH-002", "Mobile number or password is incorrect.", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED("AUTH-003", "Token has expired.", HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID("AUTH-004", "Invalid token.", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED("AUTH-005", "Access denied.", HttpStatus.FORBIDDEN),
    TOKEN_NOT_FOUND("AUTH-005", "User not found.", HttpStatus.NOT_FOUND),

    //USER
    USER_NOT_FOUND("USR-001", "User not found.", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS("USR-002", "User already exists.", HttpStatus.CONFLICT),
    MOBILE_ALREADY_EXISTS("USR-003", "Mobile number already exists.", HttpStatus.CONFLICT),
    USER_DISABLED("USR-004", "User is disabled.", HttpStatus.FORBIDDEN),

    //ROLE
    ROLE_NOT_FOUND("ROL-001", "Role not found.", HttpStatus.NOT_FOUND),
    ROLE_ALREADY_EXISTS("ROL-002", "Role already exists.", HttpStatus.CONFLICT),

    //CLINIC
    CLINIC_NOT_FOUND("TEN-001", "Clinic not found.", HttpStatus.NOT_FOUND),
    CLINIC_ALREADY_EXISTS("TEN-002", "Clinic already exists.", HttpStatus.CONFLICT),
    CLINIC_DEACTIVATE("TEN-003", "Clinic deactivate", HttpStatus.FORBIDDEN);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
