package com.roochi.petflowshared.service;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public interface BaseCommandService<CREATE_REQUEST, UPDATE_REQUEST, DELETE_REQUEST, ACTIVE_REQUEST, DEACTIVATE_REQUEST,
        CREATE_RESPONSE, UPDATE_RESPONSE, DELETE_RESPONSE, ACTIVE_RESPONSE, DEACTIVATE_RESPONSE> {

    CREATE_RESPONSE create(CREATE_REQUEST request);

    UPDATE_RESPONSE update(UPDATE_REQUEST request);

    DELETE_RESPONSE delete(DELETE_REQUEST request);

    ACTIVE_RESPONSE activate(ACTIVE_REQUEST request);

    DEACTIVATE_RESPONSE deactivate(DEACTIVATE_REQUEST request);
}
