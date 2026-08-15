package com.roochi.petflowshared.service;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public interface BaseQueryService<FIND_BY_ID_REQUEST,FIND_BY_CODE_REQUEST,FIND_ALL_REQUEST,RESPONSE> {

    RESPONSE findById(FIND_BY_ID_REQUEST request);

    List<RESPONSE> findAll(FIND_ALL_REQUEST request);

    RESPONSE findByCode(FIND_BY_CODE_REQUEST request);
}
