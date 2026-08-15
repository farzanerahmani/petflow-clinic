package com.roochi.petflowshared.validator;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
public interface Validator <T>{
    void validate(T target);
}
